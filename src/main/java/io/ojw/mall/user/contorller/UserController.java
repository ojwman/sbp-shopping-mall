package io.ojw.mall.user.contorller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.ojw.mall.user.domain.UserSignUp;
import io.ojw.mall.user.domain.User;
import io.ojw.mall.user.jwt.JwtService;
import io.ojw.mall.user.service.UserService;
import io.ojw.mall.user.validation.SignUpValidator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger("io.ojw.mall.user.contorller.UserController");
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private SignUpValidator signUpValidator;
    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> param, HttpServletResponse resp) {
	public ResponseEntity<Map<String, Object>> login(@RequestParam("id") String id
													, @RequestParam("password") String password
													, HttpServletRequest req
													, HttpServletResponse res) {
			
		// check user
		User user = userService.checkUser(id, password);
		boolean bUser = false;
		String token = null;
		if (user != null) {
			bUser = true;
			
			// make token and set response header
			token = jwtService.createToken(user.getId(), user.getName(), user.getAuth());
			res.setHeader("jwt-token", token);
		}
		
		// return
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bUser", bUser);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "[jwt-token] jwt token을 통한 사용자 정보 조회")
	@ApiImplicitParams(
			{@ApiImplicitParam(name = "jwt-token", value = "jwt-token", required = true, paramType = "header")
			}
	)
	@RequestMapping(value = "/jwt-auth/tokeninfo", method = RequestMethod.GET)
	public ResponseEntity<User> getTokenInfo(HttpServletRequest req) {
		// get token
		String token = req.getHeader("jwt-token");
		logger.debug("token: " + token);
		
		// get claims
		Claims claims = jwtService.getClaims(token);
		String id = (String) claims.get("id");
		String name = (String) claims.get("name");
		String auth = (String) claims.get("auth");
		logger.debug("claims: " + claims);
		logger.debug("cId: " + id);
		logger.debug("cId: " + name);
		logger.debug("cId: " + auth);
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAuth(auth);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jwt-auth/myinfo/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getMyInfo(@PathVariable String id, HttpServletRequest req) {
		try {
			// get token
			String token = req.getHeader("jwt-token");
			logger.debug("token: " + token);
			
			// get claims
			Claims claims = jwtService.getClaims(token);
			String cId = (String) claims.get("id");
			logger.debug("claims: " + claims);
			logger.debug("cId: " + cId);
			
			// check user
			User user = userService.getMyInfo(id);
			
			// test
			/*Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("id", id);
			User user2 = userService.getMyInfo2(mapParam);
			logger.debug("user2: " + user2);*/
			
			// return
			HttpStatus httpStatus = HttpStatus.OK;
			if (user == null) {
				httpStatus = HttpStatus.NOT_FOUND;
			}
			return new ResponseEntity<>(user, httpStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity signUp(@RequestBody @Valid UserSignUp userSignUp, Errors errors) throws Exception {
		logger.debug("param: " + userSignUp);
		
		// check default
		if (errors.hasErrors()) {
        	return ResponseEntity.badRequest().body(errors);
		}
		
		// check custom
		signUpValidator.validate(userSignUp, errors);
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }
        
        // insert user
        int result = userService.insertUser(userSignUp);
        if (result > 0) {
        	/*if (result == 1) {
        		throw new Exception("need transaction");
        	}*/
        	return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
}






























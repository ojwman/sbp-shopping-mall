package io.ojw.mall.user.contorller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ojw.mall.user.domain.User;
import io.ojw.mall.user.jwt.JwtService;
import io.ojw.mall.user.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private JwtService jwtService;
    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> name(@RequestBody Map<String, Object> param, HttpServletResponse resp) {
		try {
			// get params
			String id = (String) param.get("id");
			String password = (String) param.get("password");
			
			// check user
			User user = userService.checkUser(id, password);
			boolean bUser = false;
			String token = null;
			if (user != null) {
				bUser = true;
				
				// make token and set response header
				token = jwtService.makeJwt(user.getId(), user.getAuth());
//				System.out.println("token : " + token);
//				jwtService.checkJwt(token);
				resp.setHeader("token", token);
			}
			
			// return
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bUser", bUser);
//			map.put("token", token);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/auth/myinfo/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getMyInfo(@PathVariable String id) {
		try {
			// check user
			User user = userService.getMyInfo(id);
			
			// return
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}

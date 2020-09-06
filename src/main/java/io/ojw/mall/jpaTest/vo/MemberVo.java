package io.ojw.mall.jpaTest.vo;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class MemberVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mbrNo;
	private String id;
	private String name;

	@Builder
	public MemberVo(String id, String name) {
		this.id = id;
		this.name = name;
	}
}

//출처: https://goddaehee.tistory.com/209 [갓대희의 작은공간]
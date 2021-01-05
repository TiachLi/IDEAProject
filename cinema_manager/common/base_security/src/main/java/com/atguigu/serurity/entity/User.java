package com.atguigu.serurity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "用户实体类")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "姓名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "用户签名")
	private String token;

}




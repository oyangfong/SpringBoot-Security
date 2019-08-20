package com.forezp;

import com.forezp.service.security.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/*
  程序需要对外暴露获取Token的API接口及验证Token的API接口,故该程序也是一个资源服务
*/

@SpringBootApplication
@EnableResourceServer  // 启用资源服务器
@EnableEurekaClient
@Order(2)
public class ServiceAuthApplication {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(ServiceAuthApplication.class, args);
	}

    /*
      认证服务器配置
     */
	@Configuration
	@EnableAuthorizationServer  // 启用认证服务器
	protected  class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

		//private TokenStore tokenStore = new InMemoryTokenStore();

		JdbcTokenStore tokenStore=new JdbcTokenStore(dataSource);

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
		private UserServiceDetail userServiceDetail;


		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

			clients.inMemory()
					.withClient("browser")
					.authorizedGrantTypes("refresh_token", "password")
					.scopes("ui")
					.and()
					.withClient("service-hi")
					.secret("123456")
					.authorizedGrantTypes("client_credentials", "refresh_token","password")
					.scopes("server");

		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
					.tokenStore(tokenStore)
					.authenticationManager(authenticationManager)
					.userDetailsService(userServiceDetail);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer
					.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()");

		}
	}
}

// Copyright 2008-2009 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.arsmachina.authentication.dao.hibernate.ioc;

import org.hibernate.SessionFactory;
import org.springframework.config.java.annotation.Bean;
import org.springframework.config.java.annotation.Configuration;
import org.springframework.config.java.annotation.ExternalBean;
import org.springframework.config.java.annotation.Import;
import org.springframework.config.java.annotation.Lazy;

import br.com.arsmachina.authentication.controller.PermissionController;
import br.com.arsmachina.authentication.controller.PermissionGroupController;
import br.com.arsmachina.authentication.controller.UserController;
import br.com.arsmachina.authentication.controller.UserGroupController;
import br.com.arsmachina.authentication.controller.impl.PermissionControllerImpl;
import br.com.arsmachina.authentication.controller.impl.PermissionGroupControllerImpl;
import br.com.arsmachina.authentication.controller.impl.UserControllerImpl;
import br.com.arsmachina.authentication.controller.impl.UserGroupControllerImpl;
import br.com.arsmachina.authentication.dao.PermissionDAO;
import br.com.arsmachina.authentication.dao.PermissionGroupDAO;
import br.com.arsmachina.authentication.dao.UserDAO;
import br.com.arsmachina.authentication.dao.UserGroupDAO;
import br.com.arsmachina.authentication.dao.hibernate.PermissionDAOImpl;
import br.com.arsmachina.authentication.dao.hibernate.PermissionGroupDAOImpl;
import br.com.arsmachina.authentication.dao.hibernate.UserDAOImpl;
import br.com.arsmachina.authentication.dao.hibernate.UserGroupDAOImpl;
import br.com.arsmachina.authentication.encryption.PasswordEncrypter;
import br.com.arsmachina.dao.hibernate.ioc.PersistenceConfiguration;

/**
 * Spring JavaConfig configuration class that declares beans provided by
 * generic-authentication-hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
@Configuration(defaultLazy = Lazy.TRUE)
@Import( { PersistenceConfiguration.class })
public class GenericAuthenticationHibernateConfiguration {

	@Bean
	public UserDAO userDAO() {
		return new UserDAOImpl(sessionFactory());
	}

	@Bean(lazy = Lazy.FALSE)
	public UserController userController() {
		return new UserControllerImpl(userDAO(), passwordEncrypter(), permissionController(),
				permissionGroupController());
	}

	@Bean
	public PermissionDAO permissionDAO() {
		return new PermissionDAOImpl(sessionFactory());
	}

	@Bean
	public PermissionController permissionController() {
		return new PermissionControllerImpl(permissionDAO());
	}

	@Bean
	public PermissionGroupDAO permissionGroupDAO() {
		return new PermissionGroupDAOImpl(sessionFactory());
	}

	@Bean
	public PermissionGroupController permissionGroupController() {
		return new PermissionGroupControllerImpl(permissionGroupDAO());
	}

	@Bean
	public UserGroupDAO userGroupDAO() {
		return new UserGroupDAOImpl(sessionFactory());
	}

	@Bean
	public UserGroupController userGroupController() {
		return new UserGroupControllerImpl(userGroupDAO());
	}

	@ExternalBean
	public SessionFactory sessionFactory() {
		return null;
	}

	@ExternalBean
	public PasswordEncrypter passwordEncrypter() {
		return null;
	}

}

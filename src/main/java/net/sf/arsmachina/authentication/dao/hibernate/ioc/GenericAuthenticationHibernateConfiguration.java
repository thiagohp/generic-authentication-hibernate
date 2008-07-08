// Copyright 2008 Thiago H. de Paula Figueiredo
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

package net.sf.arsmachina.authentication.dao.hibernate.ioc;

import net.sf.arsmachina.authentication.controller.PasswordEncrypter;
import net.sf.arsmachina.authentication.controller.PermissionController;
import net.sf.arsmachina.authentication.controller.PermissionGroupController;
import net.sf.arsmachina.authentication.controller.UserController;
import net.sf.arsmachina.authentication.controller.impl.PermissionControllerImpl;
import net.sf.arsmachina.authentication.controller.impl.PermissionGroupControllerImpl;
import net.sf.arsmachina.authentication.controller.impl.UserControllerImpl;
import net.sf.arsmachina.authentication.dao.PermissionDAO;
import net.sf.arsmachina.authentication.dao.PermissionGroupDAO;
import net.sf.arsmachina.authentication.dao.UserDAO;
import net.sf.arsmachina.authentication.dao.hibernate.PermissionDAOImpl;
import net.sf.arsmachina.authentication.dao.hibernate.PermissionGroupDAOImpl;
import net.sf.arsmachina.authentication.dao.hibernate.UserDAOImpl;
import net.sf.arsmachina.dao.hibernate.spring.PersistenceConfiguration;

import org.hibernate.SessionFactory;
import org.springframework.config.java.annotation.Bean;
import org.springframework.config.java.annotation.Configuration;
import org.springframework.config.java.annotation.ExternalBean;
import org.springframework.config.java.annotation.Import;
import org.springframework.config.java.annotation.Lazy;

/**
 * Spring JavaConfig configuration class that declares beans provided by
 * generic-authentication-hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
@Configuration(defaultLazy = Lazy.TRUE)
@Import( { PersistenceConfiguration.class })
public class GenericAuthenticationHibernateConfiguration {

	@Bean
	public UserDAO userDAO() {
		return new UserDAOImpl(sessionFactory(), passwordEncrypter());
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

	@ExternalBean
	public SessionFactory sessionFactory() {
		return null;
	}

	@ExternalBean
	public PasswordEncrypter passwordEncrypter() {
		return null;
	}

}

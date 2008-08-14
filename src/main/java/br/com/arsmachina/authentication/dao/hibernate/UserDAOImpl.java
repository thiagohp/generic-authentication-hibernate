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

package br.com.arsmachina.authentication.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.com.arsmachina.authentication.controller.PasswordEncrypter;
import br.com.arsmachina.authentication.dao.UserDAO;
import br.com.arsmachina.authentication.entity.Role;
import br.com.arsmachina.authentication.entity.User;
import br.com.arsmachina.dao.SortCriterion;
import br.com.arsmachina.dao.hibernate.GenericDAOImpl;

/**
 * {@link UserDAO} implementation using Hibernate
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

	private PasswordEncrypter passwordEncrypter;

	/**
	 * Single constructor of this class.
	 * 
	 * @param sessionFactory a {@link SessionFactory}. It cannot be null.
	 * @param passwordEncrypter a {@link PasswordEncrypter}. It cannot be null.
	 */
	public UserDAOImpl(SessionFactory sessionFactory, PasswordEncrypter passwordEncrypter) {

		super(sessionFactory);

		if (passwordEncrypter == null) {
			throw new IllegalArgumentException("Parameter passwordEncrypter cannot be null");
		}

	}

	/**
	 * @see br.com.arsmachina.authentication.dao.UserDAO#findByLoginAndPassword(java.lang.String,
	 * java.lang.String)
	 */
	public User findByLoginAndPassword(String login, String password) {

		Session session = getSession();
		Query query = session.createQuery("from User where login = :login and password = :password");

		query.setParameter("login", login);
		query.setParameter("password", passwordEncrypter.encrypt(password));

		return (User) query.uniqueResult();

	}

	/**
	 * @see br.com.arsmachina.authentication.dao.UserDAO#findByLogin(java.lang.String)
	 */
	public User findByLogin(String login) {

		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("login", login));

		return (User) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public <T extends Role> List<User> findByRole(Class<T> roleClass) {

		Query query = getSession().createQuery(
				"select distinct(m.user) from Manager m order by m.user.login");

		return query.list();

	}

	/**
	 * Returns {@link Constants#ASCENDING_NAME_SORT_CRITERIA}.
	 * 
	 * @see br.com.arsmachina.dao.hibernate.GenericDAOImpl#getDefaultSortCriteria()
	 */
	@Override
	public SortCriterion[] getDefaultSortCriteria() {
		return Constants.ASCENDING_NAME_SORT_CRITERIA;
	}

}

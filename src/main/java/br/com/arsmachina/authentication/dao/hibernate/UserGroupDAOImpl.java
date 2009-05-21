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

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.com.arsmachina.authentication.dao.UserGroupDAO;
import br.com.arsmachina.authentication.entity.UserGroup;
import br.com.arsmachina.dao.SortCriterion;
import br.com.arsmachina.dao.hibernate.GenericDAOImpl;


/**
 * {@link UserGroupDAO} implementation using Hibernate
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class UserGroupDAOImpl extends GenericDAOImpl<UserGroup, Integer> implements UserGroupDAO {

	/**
	 * Single constructor of this class.
	 * 
	 * @param sessionFactory a {@link SessionFactory}. It cannot be null.
	 */
	public UserGroupDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
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

	/**
	 * @see br.com.arsmachina.authentication.dao.UserGroupDAO#findByName(java.lang.String)
	 */
	public UserGroup findByName(String name) {
		
		final Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("name", name));
		
		return (UserGroup) criteria.uniqueResult();
		
	}

}

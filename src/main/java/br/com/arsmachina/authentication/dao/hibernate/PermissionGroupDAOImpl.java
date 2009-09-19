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

package br.com.arsmachina.authentication.dao.hibernate;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.com.arsmachina.authentication.dao.PermissionGroupDAO;
import br.com.arsmachina.authentication.entity.PermissionGroup;
import br.com.arsmachina.authentication.entity.UserGroup;
import br.com.arsmachina.dao.SortCriterion;
import br.com.arsmachina.dao.hibernate.GenericDAOImpl;

/**
 * {@link PermissionGroupDAO} implementation using Hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class PermissionGroupDAOImpl extends GenericDAOImpl<PermissionGroup, Integer> implements
		PermissionGroupDAO {

	/**
	 * Single constructor of this class.
	 * 
	 * @param sessionFactory a {@link SessionFactory}. It cannot be null.
	 */
	public PermissionGroupDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * @see br.com.arsmachina.authentication.dao.PermissionGroupDAO#findByName(java.lang.String)
	 */
	public PermissionGroup findByName(String name) {

		final Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("name", name));

		return (PermissionGroup) criteria.uniqueResult();

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

	@SuppressWarnings("unchecked")
	public List<PermissionGroup> findByUserGroup(UserGroup userGroup) {
		
		Query query = getSession().createQuery("from PermissionGroup where shared = true or owner = :owner");
		query.setParameter("owner", userGroup);
		
		return query.list();
		
	}

}

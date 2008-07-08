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

package net.sf.arsmachina.authentication.dao.hibernate;

import net.sf.arsmachina.authentication.dao.PermissionGroupDAO;
import net.sf.arsmachina.authentication.entity.PermissionGroup;
import net.sf.arsmachina.dao.SortCriterion;
import net.sf.arsmachina.dao.hibernate.GenericDAOImpl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * {@link PermissionGroupDAO} implementation using Hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
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
	 * @see net.sf.arsmachina.authentication.dao.PermissionGroupDAO#findByName(java.lang.String)
	 */
	public PermissionGroup findByName(String name) {

		final Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("name", name));

		return (PermissionGroup) criteria.uniqueResult();

	}

	/**
	 * Returns {@link Constants#ASCENDING_NAME_SORT_CRITERIA}.
	 * 
	 * @see net.sf.arsmachina.dao.hibernate.GenericDAOImpl#getDefaultSortCriteria()
	 */
	@Override
	public SortCriterion[] getDefaultSortCriteria() {
		return Constants.ASCENDING_NAME_SORT_CRITERIA;
	}

}

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

import br.com.arsmachina.dao.SortCriterion;


/**
 * Utility class for declaring commons constants.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class Constants {

	/**
	 * Array with a single element: <code>new SortCriterion("name", true)</code>.
	 */
	final public static SortCriterion[] ASCENDING_NAME_SORT_CRITERIA;

	static {

		SortCriterion sortCriterion = new SortCriterion("name", true);
		ASCENDING_NAME_SORT_CRITERIA = new SortCriterion[] { sortCriterion };

	};
	
	private Constants() {
	}

}

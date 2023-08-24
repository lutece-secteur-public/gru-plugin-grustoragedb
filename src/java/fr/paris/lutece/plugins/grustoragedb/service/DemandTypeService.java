/* Copyright (c) 2002-2022, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.grustoragedb.service;

import java.util.List;

import fr.paris.lutece.plugins.grustoragedb.business.DemandType;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

public class DemandTypeService {

	
	public static final String BEAN_DEMAND_TYPE_PROVIDER = "demandType.provider";
	private static DemandTypeService _singleton;
	private IDemandTypeProvider _provider = SpringContextService.getBean( BEAN_DEMAND_TYPE_PROVIDER );

	/**
	 * Returns the unique instance
	 * 
	 * @return The unique instance
	 */
	public static DemandTypeService instance( )
	{
		if ( _singleton == null ) 
		{
			_singleton = new DemandTypeService( );
		}

		return _singleton;
	}

	/**
	 * get demand Types 
	 * 
	 * @return the demand type list
	 */
	public List<DemandType> getDemandTypes( )
	{
		return _provider.getDemandTypes( );
	}

	/**
	 * get demand Types as referencelist
	 * 
	 * @return the demand type reference list
	 */
	public ReferenceList getDemandTypesReferenceList( )
	{
		ReferenceList list = new ReferenceList( );
		
		list.addItem( "" , " ");

		for ( DemandType demandType : getDemandTypes( ) )
		{
			list.addItem( demandType.getId( ), String.valueOf(demandType.getId( )) +": " + demandType.getLabel( ) );
		}

		// getDemandTypes().stream().forEach( demandType -> list.addItem( demandType.getIdDemandType( ), demandType.getLabel( ) ) );
		
		return list;
	}



}

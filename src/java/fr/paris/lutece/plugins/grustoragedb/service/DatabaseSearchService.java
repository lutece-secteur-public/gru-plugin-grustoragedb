/*
 * Copyright (c) 2002-2015, Mairie de Paris
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

import fr.paris.lutece.plugins.gru.service.search.ISearchService;
import fr.paris.lutece.plugins.grubusiness.business.customer.Customer;
import fr.paris.lutece.plugins.grustoragedb.service.search.SearchService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import java.util.List;


/**
 * Database Search Service
 */
public class DatabaseSearchService implements ISearchService
{
    private static final String PROPERTY_AUTOCOMPLETE_URL = "grustoragedb.autocomplete.url";

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Customer> searchCustomer( String strQuery )
    {
        return SearchService.searchCustomer( strQuery );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isAutoComplete(  )
    {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAutoCompleteUrl(  )
    {
        return AppPropertiesService.getProperty( PROPERTY_AUTOCOMPLETE_URL );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Customer searchCustomerById( String strCustomerId )
    {
        return SearchService.searchCustomerById( strCustomerId );
    }

}

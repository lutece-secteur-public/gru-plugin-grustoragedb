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

import fr.paris.lutece.plugins.gru.service.search.CustomerResult;
import fr.paris.lutece.plugins.grustoragedb.service.lucene.SearchService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;


/**
 * AutoCompleteService
 */
public final class AutoCompleteService
{
    private static final int INDENT = 4;

    /** Private constructor */
    private AutoCompleteService()
    {
    }
            
    /**
     * Returns a json string for autocomplete purpose 
     * @param strQuery The query
     * @return The JSON
     */
    public static String getJson( String strQuery )
    {
        List<CustomerResult> listCustomers = SearchService.searchCustomer( strQuery );
        JSONArray jsonAutocomplete = new JSONArray(  );

        for ( CustomerResult customer : listCustomers )
        {
            JSONObject jsonItem = new JSONObject(  );
            jsonItem.accumulate( "first_name", customer.getFirstname(  ) );
            jsonItem.accumulate( "last_name", customer.getLastname(  ) );
            jsonAutocomplete.add( jsonItem );
        }

        return jsonAutocomplete.toString( INDENT );
    }
}
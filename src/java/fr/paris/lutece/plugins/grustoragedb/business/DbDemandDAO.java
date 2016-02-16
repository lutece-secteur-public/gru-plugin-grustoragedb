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
package fr.paris.lutece.plugins.grustoragedb.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides Data Access methods for DbDemand objects
 */
public final class DbDemandDAO implements IDbDemandDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_demand ) FROM grustoragedb_demand";
    private static final String SQL_QUERY_SELECT = "SELECT id_demand, customer_id, demand_id, demand_type_id, demand_reference, demand_state, max_steps, current_step, status_customer, status_gru FROM grustoragedb_demand WHERE id_demand = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO grustoragedb_demand ( id_demand, customer_id, demand_id, demand_type_id, demand_reference, demand_state, max_steps, current_step, status_customer, status_gru ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM grustoragedb_demand WHERE id_demand = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE grustoragedb_demand SET id_demand = ?, customer_id = ?, demand_id = ?, demand_type_id = ?, demand_reference = ?, demand_state = ?, max_steps = ?, current_step = ? , status_customer = ?, status_gru = ? WHERE id_demand = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_demand, customer_id, demand_id, demand_type_id, demand_reference, demand_state, max_steps, current_step, status_customer, status_gru FROM grustoragedb_demand";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_demand FROM grustoragedb_demand";
    private static final String SQL_QUERY_SELECT_BY_ID_AND_TYPE = "SELECT id_demand, customer_id, demand_id, demand_type_id, demand_reference, demand_state, max_steps, current_step, status_customer, status_gru FROM grustoragedb_demand WHERE demand_id = ? AND demand_type_id = ?";
    private static final String SQL_QUERY_SELECT_BY_CUSTOMER = "SELECT id_demand, customer_id, demand_id, demand_type_id, demand_reference, demand_state, max_steps, current_step, status_customer, status_gru FROM grustoragedb_demand WHERE customer_id = ?";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey = 1;

        if ( daoUtil.next(  ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free(  );

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( DbDemand demand, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        demand.setId( newPrimaryKey( plugin ) );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, demand.getId(  ) );

        daoUtil.setString( nIndex++, demand.getCustomerId(  ) );
        daoUtil.setString( nIndex++, demand.getDemandId(  ) );
        daoUtil.setString( nIndex++, demand.getDemandTypeId(  ) );
        daoUtil.setString( nIndex++, demand.getReference(  ) );
        daoUtil.setInt( nIndex++, demand.getDemandState(  ) );
        daoUtil.setInt( nIndex++, demand.getMaxSteps(  ) );
        daoUtil.setInt( nIndex++, demand.getCurrentStep(  ) );
        daoUtil.setString( nIndex++, demand.getStatusForCustomer(  ) );
        daoUtil.setString( nIndex++, demand.getStatusForGRU(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DbDemand load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery(  );

        DbDemand demand = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            demand = new DbDemand(  );
            demand.setId( daoUtil.getInt( nIndex++ ) );
            demand.setCustomerId( daoUtil.getString( nIndex++ ) );
            demand.setDemandId( daoUtil.getString( nIndex++ ) );
            demand.setDemandTypeId( daoUtil.getString( nIndex++ ) );
            demand.setReference( daoUtil.getString( nIndex++ ) );
            demand.setDemandState( daoUtil.getInt( nIndex++ ) );
            demand.setMaxSteps( daoUtil.getInt( nIndex++ ) );
            demand.setCurrentStep( daoUtil.getInt( nIndex++ ) );
            demand.setStatusForCustomer( daoUtil.getString( nIndex++ ) );
            demand.setStatusForGRU( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free(  );

        return demand;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( DbDemand demand, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        int nIndex = 1;
        daoUtil.setInt( nIndex++, demand.getId(  ) );
        daoUtil.setString( nIndex++, demand.getCustomerId(  ) );
        daoUtil.setString( nIndex++, demand.getDemandId(  ) );
        daoUtil.setString( nIndex++, demand.getDemandTypeId(  ) );
        daoUtil.setString( nIndex++, demand.getReference(  ) );
        daoUtil.setInt( nIndex++, demand.getDemandState(  ) );
        daoUtil.setInt( nIndex++, demand.getMaxSteps(  ) );
        daoUtil.setInt( nIndex++, demand.getCurrentStep(  ) );
        daoUtil.setString( nIndex++, demand.getStatusForCustomer(  ) );
        daoUtil.setString( nIndex++, demand.getStatusForGRU(  ) );
        daoUtil.setInt( nIndex, demand.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<DbDemand> selectDemandsList( Plugin plugin )
    {
        List<DbDemand> demandList = new ArrayList<DbDemand>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            DbDemand demand = new DbDemand(  );
            int nIndex = 1;
            demand.setId( daoUtil.getInt( nIndex++ ) );
            demand.setCustomerId( daoUtil.getString( nIndex++ ) );
            demand.setDemandId( daoUtil.getString( nIndex++ ) );
            demand.setDemandTypeId( daoUtil.getString( nIndex++ ) );
            demand.setReference( daoUtil.getString( nIndex++ ) );
            demand.setDemandState( daoUtil.getInt( nIndex++ ) );
            demand.setMaxSteps( daoUtil.getInt( nIndex++ ) );
            demand.setCurrentStep( daoUtil.getInt( nIndex++ ) );
            demand.setStatusForCustomer( daoUtil.getString( nIndex++ ) );
            demand.setStatusForGRU( daoUtil.getString( nIndex++ ) );

            demandList.add( demand );
        }

        daoUtil.free(  );

        return demandList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdDemandsList( Plugin plugin )
    {
        List<Integer> demandList = new ArrayList<Integer>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            demandList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free(  );

        return demandList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public DbDemand selectByIdAndType( String strDemandId, String strDemandTypeId, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ID_AND_TYPE, plugin );
        daoUtil.setString( 1, strDemandId );
        daoUtil.setString( 2, strDemandTypeId );
        daoUtil.executeQuery(  );

        DbDemand demand = null;

        if ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            demand = new DbDemand(  );
            demand.setId( daoUtil.getInt( nIndex++ ) );
            demand.setCustomerId( daoUtil.getString( nIndex++ ) );
            demand.setDemandId( daoUtil.getString( nIndex++ ) );
            demand.setDemandTypeId( daoUtil.getString( nIndex++ ) );
            demand.setReference( daoUtil.getString( nIndex++ ) );
            demand.setDemandState( daoUtil.getInt( nIndex++ ) );
            demand.setMaxSteps( daoUtil.getInt( nIndex++ ) );
            demand.setCurrentStep( daoUtil.getInt( nIndex++ ) );
            demand.setStatusForCustomer( daoUtil.getString( nIndex++ ) );
            demand.setStatusForGRU( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free(  );

        return demand;
    }

    @Override
    public List<DbDemand> selectByCustomer( String strCustomerId, Plugin plugin )
    {
        List<DbDemand> demandList = new ArrayList<DbDemand>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CUSTOMER, plugin );
        daoUtil.setString( 1, strCustomerId );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            DbDemand demand = new DbDemand(  );
            int nIndex = 1;
            demand.setId( daoUtil.getInt( nIndex++ ) );
            demand.setCustomerId( daoUtil.getString( nIndex++ ) );
            demand.setDemandId( daoUtil.getString( nIndex++ ) );
            demand.setDemandTypeId( daoUtil.getString( nIndex++ ) );
            demand.setReference( daoUtil.getString( nIndex++ ) );
            demand.setDemandState( daoUtil.getInt( nIndex++ ) );
            demand.setMaxSteps( daoUtil.getInt( nIndex++ ) );
            demand.setCurrentStep( daoUtil.getInt( nIndex++ ) );
            demand.setStatusForCustomer( daoUtil.getString( nIndex++ ) );
            demand.setStatusForGRU( daoUtil.getString( nIndex++ ) );

            demandList.add( demand );
        }

        daoUtil.free(  );

        return demandList;
    }
}

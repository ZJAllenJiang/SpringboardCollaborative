import React, { useCallback, useMemo } from 'react';
import { styled } from '@mui/material/styles';
import MainCard from 'ui-component/cards/MainCard';
import Stack from '@mui/material/Stack';
import RefreshIcon from '@mui/icons-material/Refresh';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DownloadIcon from '@mui/icons-material/Download';
import Button from '@mui/material/Button';
import Chip from '@mui/material/Chip';
import Link from '@mui/material/Link';
import { useNavigate } from 'react-router-dom';
import {
    GridRowsProp,
    GridRowModesModel,
    GridRowModes,
    DataGrid,
    GridColDef,
    GridToolbarContainer,
    GridActionsCellItem,
    GridEventListener,
    GridRowId,
    GridRowModel,
    GridRowEditStopReasons,
  } from '@mui/x-data-grid';
  import {
    randomCreatedDate,
    randomTraderName,
    randomId,
    randomArrayItem,
  } from '@mui/x-data-grid-generator';
import { mockCases } from './constants';
import docuSignLogo from 'assets/images/docuSign-logo.jpeg';
import zoomLogo from 'assets/images/zoom-logo.png';

// styles
const StyledRoot = styled(MainCard)(({ theme }) => ({
    minHeight: '100%',
    display: 'flex',
    flexDirection: 'column',

}));

const rows = mockCases;

const Cases = () => {
    const navigate = useNavigate();

    const handleEditClick = useCallback((id) => () => {
        navigate(`edit/${id}`);
    }, [navigate]);

    const columns = useMemo(() => [
        { field: 'taskId', headerName: 'Case ID', width: 60 },
        {
            field: 'tscAdministratorId',
            headerName: 'TSC Admin ID', 
            width: 100
        },
        {
            field: 'navigatorId',
            headerName: 'Navigator ID',
            width: 100
        },
        {
            field: 'taskProgressEnum',
            headerName: 'Progress',
            sortable: false,
            width: 140,
            renderCell: (params) => {
                switch (params.value) {
                    case 'START':
                        return <Chip label={params.value} color="info" />;
                    case 'IN_PROGRESS':
                        return <Chip label={params.value} color="primary" />;
                    case 'COMPLETED':
                        return <Chip label={params.value} color="success" />;
                    default:
                        return <Chip label={params.value} color="default" />;
                }
            },
        },
        {
            field: 'consenus',
            headerName: 'Consenus',
            sortable: false,
            flex: 1,
            renderHeader: (params) => (
                // <strong style={{display: 'flex', alignItems: 'center'}}>
                //   {'Consenus '}
                  <img src={docuSignLogo} height="20" />
                // </strong>
              ),
            renderCell: (params) => {
                const fileNames = params.value;
                return fileNames?.map((fileName, index) => (
                    <Chip 
                        key={`${index}-${fileName}`} 
                        label={fileName} 
                        color="primary" 
                        size="small" 
                        variant="outlined" 
                        sx={{ ml: index ? 0.5 : 0 }}
                        onClick={() => {}}
                    />
                )) ?? <></>;
            },
        },
        {
            field: 'appointment',
            headerName: 'Appointment',
            sortable: false,
            flex: 1,
            renderHeader: (params) => (
                // <strong style={{display: 'flex', alignItems: 'center'}}>
                //   {'Consenus '}
                  <img src={zoomLogo} height="12" />
                // </strong>
              ),
            renderCell: (params) => {
                const { time, url } = params.value;
                return <Link href="https://citi.zoom.us/j/8816510783?pwd=TGtlUDNicWtnT0cvQ1VwM1N1NVNnQT09" target="_blank" color="inherit" underline="always">
                  {time}
                </Link>
            },
        },
        {
            field: 'actions',
            type: 'actions',
            headerName: 'Actions',
            width: 80,
            cellClassName: 'actions',
            getActions: ({ id }) => {
                return [
                    <GridActionsCellItem
                        icon={<EditIcon />}
                        label="Edit"
                        className="textPrimary"
                        onClick={handleEditClick(id)}
                        color="inherit"
                    />,
                ];
            },
        }
    ], [handleEditClick]);

    const handleAdd = useCallback(() => {
        navigate('add');
    }, [navigate]);

    return (
        <StyledRoot>
            <Stack direction="row" spacing={1} sx={{ mb: 1 }}>
                <Button variant="outlined"><RefreshIcon />Refresh</Button>
                <Button variant="outlined" onClick={handleAdd}><AddIcon />Add New</Button>
                <Button variant="outlined"><DownloadIcon />Export</Button>
            </Stack>
            <DataGrid
                rows={rows}
                columns={columns}
                getRowId={(row) => row.taskId}
                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 12,
                        },
                    },
                }}
                pageSizeOptions={[5]}
                // checkboxSelection
                disableRowSelectionOnClick
            />
        </StyledRoot>
    );
}

export default Cases;
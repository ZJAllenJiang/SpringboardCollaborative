import React, { useCallback, useMemo } from 'react';
import { styled } from '@mui/material/styles';
import MainCard from 'ui-component/cards/MainCard';
import Stack from '@mui/material/Stack';
import RefreshIcon from '@mui/icons-material/Refresh';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DownloadIcon from '@mui/icons-material/Download';
import Button from '@mui/material/Button';
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

// styles
const StyledRoot = styled(MainCard)(({ theme }) => ({
    minHeight: '100%',
    display: 'flex',
    flexDirection: 'column',

}));

const rows = [
    { id: 1, lastName: 'Snow', firstName: 'Jon', age: 35 },
    { id: 2, lastName: 'Lannister', firstName: 'Cersei', age: 42 },
    { id: 3, lastName: 'Lannister', firstName: 'Jaime', age: 45 },
    { id: 4, lastName: 'Stark', firstName: 'Arya', age: 16 },
    { id: 5, lastName: 'Targaryen', firstName: 'Daenerys', age: 32 },
    { id: 6, lastName: 'Melisandre', firstName: null, age: 50 },
    { id: 7, lastName: 'Clifford', firstName: 'Ferrara', age: 44 },
    { id: 8, lastName: 'Frances', firstName: 'Rossini', age: 36 },
    { id: 9, lastName: 'Roxie', firstName: 'Harvey', age: 65 },
];

const Recipients = () => {
    const navigate = useNavigate();

    const handleEditClick = useCallback((id) => () => {
        navigate(`edit/${id}`);
    }, [navigate]);

    const columns = useMemo(() => [
        { field: 'id', headerName: 'ID', width: 90 },
        {
            field: 'firstName',
            headerName: 'First name',
            width: 150,
            // editable: true,
        },
        {
            field: 'lastName',
            headerName: 'Last name',
            width: 150,
        },
        {
            field: 'age',
            headerName: 'Age',
            type: 'number',
            width: 110,
        },
        {
            field: 'fullName',
            headerName: 'Full name',
            description: 'This column has a value getter and is not sortable.',
            sortable: false,
            width: 160,
            valueGetter: (params) =>
                `${params.row.firstName || ''} ${params.row.lastName || ''}`,
        },
        {
            field: 'actions',
            type: 'actions',
            headerName: 'Actions',
            width: 100,
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

    return (
        <StyledRoot>
            <Stack direction="row" spacing={1} sx={{ mb: 1 }}>
                <Button variant="outlined"><RefreshIcon />Refresh</Button>
                <Button variant="outlined"><AddIcon />Add New</Button>
                <Button variant="outlined"><DownloadIcon />Export</Button>
            </Stack>
            <DataGrid
                rows={rows}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 5,
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

export default Recipients;
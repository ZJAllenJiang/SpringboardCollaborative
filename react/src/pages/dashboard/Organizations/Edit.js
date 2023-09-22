import React from 'react';
import { styled } from '@mui/material/styles';
import MainCard from 'ui-component/cards/MainCard';
import Breadcrumbs from 'ui-component/extended/Breadcrumbs';
import { IconChevronRight } from '@tabler/icons';
import { useNavigate, useLocation } from 'react-router';

// styles
const StyledRoot = styled(MainCard)(({ theme }) => ({
    minHeight: '100%',
    display: 'flex',
    flexDirection: 'column',
    '& .MuiCardContent-root': {
        flex: 1,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'stretch',
        '& .MuiPaper-root': {
            flex: 1,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'stretch',
            '& .form-container': {
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
            }
        }
    },
}));

const Edit = () => {
    const location = useLocation();

    return (
        <>
            <StyledRoot>Edit</StyledRoot>
        </>
    );
}

export default Edit;
import React, { useState, useCallback, useMemo, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import CloseIcon from '@mui/icons-material/Close';

const StyledRoot = styled(Box)(({ theme }) => ({
    width: '100%',
    height: '100%',
    border: `1px ${theme.palette.primary.main} dashed`,
    transition: 'all 0.5s ease',
    borderRadius: 36,
    '& .file-btn': {
        width: '100%',
        height: '100%',
    }
}));

const VisuallyHiddenInput = styled('input')({
    clip: 'rect(0 0 0 0)',
    clipPath: 'inset(50%)',
    height: 1,
    overflow: 'hidden',
    position: 'absolute',
    bottom: 0,
    left: 0,
    whiteSpace: 'nowrap',
    width: 1,
});

const Upload = (props) => {
    const { file, onChange } = props;
    const fileName = useMemo(() => {
        if (file) {
            const arr = file.name.split('.');
            const format = arr.pop();
            arr.push(format.toLowerCase());
            return arr.join('.');
        }
        return '';
    }, [file]);

    const handleChange = useCallback((event) => {
        onChange(event.target.files[0]);
    }, []);

    const handleDelete = useCallback((event) => {
        event.preventDefault();
        event.stopPropagation();
        onChange(null);
    }, []);

    return (
        <StyledRoot>
            <Button className="file-btn" component="label" variant="text" startIcon={file ? <></> : <CloudUploadIcon />}>
                {file ? <Button variant="outlined" endIcon={<CloseIcon />} sx={{textTransform: 'none'}} onClick={handleDelete}>
                    {fileName}
                </Button> : 'Click or drop file here'}
                {!file && <VisuallyHiddenInput type="file" onChange={handleChange} />}
            </Button>
        </StyledRoot>
    );
}

export default Upload;
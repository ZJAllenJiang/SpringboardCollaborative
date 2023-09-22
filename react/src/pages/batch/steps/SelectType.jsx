import React, { useState, useCallback, useEffect } from 'react';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { styled } from '@mui/material/styles';

const StyledRoot = styled(FormControl)(({ theme }) => ({
    transform: 'translateY(-50%)',
}));

const SelectType = (props) => {
    const { type, onChange } = props;

    const handleChange = (event) => {
        onChange(event.target.value);
    };

    return (
        <StyledRoot>
            <RadioGroup
                name="type"
                value={type}
                onChange={handleChange}
            >
                <FormControlLabel value="add" control={<Radio />} label="Add" />
                <FormControlLabel value="update" control={<Radio />} label="Update" />
                <FormControlLabel value="addOrUpdate" control={<Radio />} label="Add or Update" />
                <FormControlLabel value="delete" control={<Radio />} label="Delete" />
            </RadioGroup>
        </StyledRoot>
    );
}

export default SelectType;
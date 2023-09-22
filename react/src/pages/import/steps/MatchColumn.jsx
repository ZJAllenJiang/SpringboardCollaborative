import React, { useState, useCallback, useEffect, useMemo } from 'react';
import Grid from '@mui/material/Grid';
import List from '@mui/material/List';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemIcon from '@mui/material/ListItemIcon';
import Checkbox from '@mui/material/Checkbox';
import Button from '@mui/material/Button';
import Divider from '@mui/material/Divider';
import SubCard from 'ui-component/cards/SubCard';
import { styled } from '@mui/material/styles';
import { predefinedKeys } from '../constants';
import Radio from '@mui/material/Radio';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';

const StyledCard = styled(Card)(({ theme }) => ({
    border: '1px solid #eef2f6',
    '&:hover': {
        boxShadow: '0 2px 14px 0 rgb(32 40 45 / 8%)',
    },
    '& .MuiList-root': {
        width: '100%',
        flex: 1,
    },
}));

const StyledRoot = styled(Grid)(({ theme }) => ({
    alignSelf: 'stretch',
    '& .list-container > .MuiPaper-root': {
        minHeight: '100%',
    },
}));

function not(a, b) {
    return a.filter((value) => b.indexOf(value) === -1);
}

function intersection(a, b) {
    return a.filter((value) => b.indexOf(value) !== -1);
}

function union(a, b) {
    return [...a, ...not(b, a)];
}

const MatchColumn = (props) => {
    const { fileJson, checked, left, right, onCheckedChange, onLeftChange, onRightChange } = props;

    const columnNames = useMemo(() => {
        return fileJson[0] ?? [];
    }, [fileJson]);

    const leftChecked = intersection(checked, left);
    const rightChecked = intersection(checked, right);

    const handleToggle = (value, single = false) => () => {
        if (single) {
            onCheckedChange([value]);
        } else {
            const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }
        onCheckedChange(newChecked);
        }
        
    };

    const numberOfChecked = (items) =>
        intersection(checked, items).length;

    const handleToggleAll = (items) => () => {
        if (numberOfChecked(items) === items.length) {
            onCheckedChange(not(checked, items));
        } else {
            onCheckedChange(union(checked, items));
        }
    };

    const handleCheckedRight = () => {
        onRightChange(right.concat(leftChecked));
        onLeftChange(not(left, leftChecked));
        onCheckedChange(not(checked, leftChecked));
    };

    const handleCheckedLeft = () => {
        onLeftChange(left.concat(rightChecked));
        onRightChange(not(right, rightChecked));
        onCheckedChange(not(checked, rightChecked));
    };

    const predefinedNamesToSelect = (items) => {
        return <StyledCard>
            <TableCell>
                <Typography variant="subtitle1">Predefined Names to Select</Typography>
                <Typography variant="subtitle2">Move one to match with the left column</Typography>
            </TableCell>
            <List
                sx={{
                    width: 200,
                    height: 230,
                    bgcolor: 'background.paper',
                    overflow: 'auto',
                }}
                dense
                component="div"
                role="list"
            >
                {items?.map((value) => {
                    const labelId = `transfer-list-all-item-${value}-label`;

                    return (
                        <ListItem
                            key={value}
                            role="listitem"
                            button
                            onClick={handleToggle(value, true)}
                        >
                            <ListItemIcon>
                                <Radio
                                    checked={checked.indexOf(value) !== -1}
                                    tabIndex={-1}
                                    disableRipple
                                    inputProps={{ 'aria-labelledby': labelId }}
                                />
                            </ListItemIcon>
                            <ListItemText id={labelId} primary={value} />
                        </ListItem>
                    );
                })}
            </List>
        </StyledCard>
    }

    const predefinedNamesSelected = (items) => {
        return <StyledCard>
            <TableContainer component={Paper}>
                <Table >
                    <TableHead>
                        <TableRow>
                            <TableCell>
                                <Typography variant="subtitle1">Column Header</Typography>
                                <Typography variant="subtitle2">Read from uploaded file</Typography>
                            </TableCell>
                            <TableCell sx={{ display: 'flex' }}>
                                <Checkbox
                                    onClick={handleToggleAll(items)}
                                    checked={numberOfChecked(items) === items.length && items.length !== 0}
                                    indeterminate={
                                        numberOfChecked(items) !== items.length && numberOfChecked(items) !== 0
                                    }
                                    disabled={items.length === 0}
                                    inputProps={{
                                        'aria-label': 'all items selected',
                                    }}
                                />
                                <div>
                                    <Typography variant="subtitle1">Predefined Names Matched</Typography>
                                    <Typography variant="subtitle2">{`${numberOfChecked(items)}/${items.length} selected`}</Typography>
                                </div>
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {columnNames?.map((columnName, index) => {
                            const selectedItem = items[index] ?? '';
                            const labelId = `transfer-list-all-item-${selectedItem}-label`;

                            return (
                                <TableRow
                                    key={columnName}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell component="th" scope="row">
                                        {columnName}
                                    </TableCell>
                                    <TableCell style={{ paddingTop: 0, paddingBottom: 0 }}>
                                        {selectedItem && <ListItem
                                            key={selectedItem}
                                            role="listitem"
                                            button
                                            onClick={handleToggle(selectedItem)}
                                            sx={{ p: 0 }}
                                        >
                                            <ListItemIcon>
                                                <Checkbox
                                                    checked={checked.indexOf(selectedItem) !== -1}
                                                    tabIndex={-1}
                                                    disableRipple
                                                    inputProps={{
                                                        'aria-labelledby': labelId,
                                                    }}
                                                />
                                            </ListItemIcon>
                                            <ListItemText id={labelId} primary={selectedItem} />
                                        </ListItem>}
                                    </TableCell>
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </TableContainer>

        </StyledCard>
    }

    return (
        <StyledRoot container spacing={2} justifyContent="center" alignItems="stretch">
            <Grid item xs={7} className="list-container">{predefinedNamesSelected(left)}</Grid>
            <Grid item xs={1} alignSelf="center">
                <Grid container direction="column" alignItems="center">
                    <Button
                        sx={{ my: 1 }}
                        variant="outlined"
                        size="small"
                        onClick={handleCheckedRight}
                        disabled={leftChecked.length === 0}
                        aria-label="move selected right"
                    >
                        &gt;
                    </Button>
                    <Button
                        sx={{ my: 0.5 }}
                        variant="outlined"
                        size="small"
                        onClick={handleCheckedLeft}
                        disabled={rightChecked.length === 0 || left.length === columnNames.length}
                        aria-label="move selected left"
                    >
                        &lt;
                    </Button>
                </Grid>
            </Grid>
            <Grid item xs={4} className="list-container">{predefinedNamesToSelect(right)}</Grid>
        </StyledRoot>
    );
}

export default MatchColumn;

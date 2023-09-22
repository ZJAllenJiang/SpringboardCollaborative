import React, { useMemo, useCallback, useEffect, useState } from 'react';
import * as XLSX from 'xlsx';
import { styled } from '@mui/material/styles';
import { Card } from '@mui/material';
import { useLocation, useNavigate } from 'react-router';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import SelectType from './steps/SelectType';
import Upload from './steps/Upload';
import MatchColumn from './steps/MatchColumn';

// project imports
import MainCard from 'ui-component/cards/MainCard';
import { predefinedKeys } from './constants';
import { batchSubmitrecipients } from './api';

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

const steps = ['Select a type', 'Upload a sheet', 'Match columns'];

// =============================|| TABLER ICONS ||============================= //

const Batch = () => {
    const [activeStep, setActiveStep] = React.useState(0);
    // step1 select type
    const [type, setType] = useState('addOrUpdate');
    // step2 upload file
    const [file, setFile] = useState();
    const [fileJson, setFileJson] = useState([]);

    const location = useLocation();

    const importFor = useMemo(() => {
        let str = location.pathname.split('/').at(-1);
        str = str.charAt(0).toUpperCase() + str.slice(1);
        return str;
    }, [location]);

    // step2 match column
    const [checked, setChecked] = useState([]);
    const [left, setLeft] = useState([]);
    const [right, setRight] = useState(predefinedKeys[importFor]);

    const canNext = useMemo(() => {
        switch (activeStep) {
            case 0:
                return type;
            case 1:
                return file && fileJson.length > 1 && fileJson[0]?.length;
            case 2:
                return fileJson[0]?.length === left.length;
            default:
                return false;
        }
    }, [activeStep, type, file, fileJson, left]);

    const handleUploadChange = async (_file) => {
        if (_file) {
            const data = await _file.arrayBuffer();
            const workbook = XLSX.read(data, { cellStyles: true });
            const worksheet = workbook.Sheets[workbook.SheetNames[0]];
            const jsonData = XLSX.utils.sheet_to_json(worksheet, {
                header: 1,
                defval: '',
            });
            setFileJson(jsonData);
        } else {
            setFileJson([]);
        }
        setFile(_file);
    }

    const handleNext = useCallback(() => {
        switch (activeStep) {
            case steps.length - 1:
                batchSubmitrecipients({fileJson, left});
                break;
            default:
                break;
        }
        setActiveStep(activeStep + 1);
    }, [activeStep, fileJson, left, steps]);

    const handleBack = useCallback(() => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    }, []);

    const handleReset = useCallback(() => {
        setActiveStep(0);
        setType('addOrUpdate');
        setFile();
        setFileJson([]);
        setChecked([]);
        setLeft([]);
        setRight(predefinedKeys[importFor])
    }, []);

    useEffect(() => {
        handleReset();
    }, [importFor, handleReset]);

    return (
        <StyledRoot title={`Import ${importFor}`}>
            <Card sx={{ overflow: 'hidden' }}>
                <Stepper activeStep={activeStep} alternativeLabel>
                    {steps.map((label, index) => {
                        return (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        );
                    })}
                </Stepper>
                <Box className="form-container" sx={{ display: 'flex', flex: 1, pt: 2 }}>
                    {activeStep === 0 && <SelectType type={type} onChange={setType} />}
                    {activeStep === 1 && <Upload file={file} onChange={handleUploadChange} />}
                    {activeStep === 2 && <MatchColumn
                        importFor={importFor}
                        fileJson={fileJson}
                        checked={checked}
                        left={left}
                        right={right}
                        onCheckedChange={setChecked}
                        onLeftChange={setLeft}
                        onRightChange={setRight}
                    />}
                    {activeStep === 3 && <Typography sx={{ mt: 2, mb: 1 }}>
                        All steps completed - you&apos;re finished
                    </Typography>}
                </Box>
                {activeStep === steps.length ? (
                    <React.Fragment>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleReset}>Start Over</Button>
                        </Box>
                    </React.Fragment>
                ) : (
                    <React.Fragment>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Button
                                color="inherit"
                                disabled={activeStep === 0}
                                onClick={handleBack}
                                sx={{ mr: 1 }}
                            >
                                Back
                            </Button>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleNext} disabled={!canNext}>
                                {activeStep === steps.length - 1 ? 'Finish' : 'Next'}
                            </Button>
                        </Box>
                    </React.Fragment>
                )}
            </Card>
        </StyledRoot>
    );
}

export default Batch;
import React from 'react';
import { Box } from '@mui/material';
import Post1 from './Post1';
import Post2 from './Post2';
import Post3 from './Post3';

const Org = () => {
  return (
    <React.Fragment>
      <Box>
        <Post1 />
        <Post2 />
        <Post3 />
      </Box>
    </React.Fragment>
  );
};

export default Org;

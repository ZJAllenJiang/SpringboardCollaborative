import React from 'react';
import {
  Favorite,
  FavoriteBorder,
  MoreVert,
  Share,
  Comment,
} from '@mui/icons-material';
import {
  Avatar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  Checkbox,
  IconButton,
  Typography,
} from '@mui/material';
import { Link } from 'react-router-dom';

const Post3 = () => {
  return (
    <div>
      <Card sx={{ marginBottom: 5 }}>
        <CardHeader
          avatar={
            <Link to='/profilepage' style={{ textDecoration: 'none' }}>
              <Avatar sx={{ bgcolor: 'yellow' }} aria-label='recipe'>
                H
              </Avatar>
            </Link>
          }
          action={
            <IconButton aria-label='settings'>
              <MoreVert />
            </IconButton>
          }
          title='HIJK'
          subheader='June 11, 2023'
        />
        <CardMedia
          component='img'
          height='194'
          image='https://wordpress.bigissue.com/wp-content/uploads/2017/12/Homelessness-Manchester-Smabs-Sputzer-hero1.jpg'
          alt='Paella dish'
        />
        <CardContent>
          <Typography variant='body2' color='text.secondary'>
            Since their founding, the Coalition for the Homeless has supported
            people suffering in extreme poverty, including homeless people. For
            example, in 2022, their Grand Central Food Program provided 354,000
            meals to homeless people at 25 locations in New York. Their eviction
            prevention program also saved 613 families from becoming homeless by
            providing one-time rental arrears grants. Furthermore, they housed
            222 homeless children at Camp Homeward Bound in the summer. They
            also provided stable, permanent housing to 169 residents. In
            addition, they helped 62 chronically homeless individuals with
            physical or mental disabilities to secure government benefits and
            obtain supportive housing.
          </Typography>
        </CardContent>
        <CardActions>
          <IconButton aria-label='add to favorites'>
            <Checkbox
              icon={<FavoriteBorder />}
              checkedIcon={<Favorite sx={{ color: 'red' }} />}
            />
          </IconButton>
          <IconButton aria-label='comment'>
            <Comment />
          </IconButton>
          <IconButton aria-label='share'>
            <Share />
          </IconButton>
        </CardActions>
      </Card>
    </div>
  );
};

export default Post3;

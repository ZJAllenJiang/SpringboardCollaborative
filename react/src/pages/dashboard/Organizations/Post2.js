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

const Post2 = () => {
  return (
    <div>
      <Card sx={{ marginBottom: 5 }}>
        <CardHeader
          avatar={
            <Link to='/profilepage' style={{ textDecoration: 'none' }}>
              <Avatar sx={{ bgcolor: 'yellow' }} aria-label='recipe'>
                E
              </Avatar>
            </Link>
          }
          action={
            <IconButton aria-label='settings'>
              <MoreVert />
            </IconButton>
          }
          title='EFG'
          subheader='July 11, 2023'
        />
        <CardMedia
          component='img'
          height='194'
          image='https://www.qualityformationsblog.co.uk/wp-content/uploads/2020/02/QCF-Volunteer.jpg'
          alt='Paella dish'
        />
        <CardContent>
          <Typography variant='body2' color='text.secondary'>
            Help bring clean and safe water to every person on the planet. The
            water crisis is massive. But together, we can solve it. Private
            donors cover our operating costs so whether you choose to join us by
            giving once or through The Spring, our community of monthly givers,
            know that 100% of your generosity will bring clean water to families
            around the world.
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

export default Post2;

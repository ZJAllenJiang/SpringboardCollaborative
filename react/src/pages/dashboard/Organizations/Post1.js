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

const Post1 = () => {
  return (
    <div>
      <Card sx={{ marginBottom: 5 }}>
        <CardHeader
          avatar={
            <Link to='/profilepage' style={{ textDecoration: 'none' }}>
              <Avatar sx={{ bgcolor: 'yellow' }} aria-label='recipe'>
                A
              </Avatar>
            </Link>
          }
          action={
            <IconButton aria-label='settings'>
              <MoreVert />
            </IconButton>
          }
          title='ABC'
          subheader='September 22, 2023'
        />
        <CardMedia
          component='img'
          height='194'
          image='https://media.istockphoto.com/id/178447404/photo/modern-business-buildings.jpg?s=1024x1024&w=is&k=20&c=w15AGFL7iZtN6aXbIlrUWZbkft2HtZq9TnKrmp5Jr2g='
          alt='Paella dish'
        />
        <CardContent>
          <Typography variant='body2' color='text.secondary'>
            We work in the hardest-to-reach places, where it’s toughest to be a
            child.When you give to Save the Children, 85% of every dollar goes
            straight to our mission to provide lifesaving relief to children.
            $50 can provide enough food to keep 3 children from going hungry for
            a month $150 can wrap 30 warm, cozy blankets around children
            affected by conflict $300 can provide 150 face masks to refugee
            health workers on the front lines With your support, we can continue
            our work to keep children in the U.S. and around the world healthy,
            educated and safe.
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

export default Post1;

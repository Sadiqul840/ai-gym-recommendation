

// import React, { useEffect, useState } from 'react';
// import { Grid, Card, CardContent, Typography } from '@mui/material';
// import { useNavigate } from 'react-router-dom';
// import { getActivities } from '../services/api';

// const ActivityList = ({ refreshKey }) => {
//   const [activities, setActivities] = useState([]);
//   const navigate = useNavigate();

//   const fetchActivities = async () => {
//     try {
//       const response = await getActivities();
//       setActivities(response.data);
//     } catch (error) {
//       console.error('Error fetching activities:', error);
//     }
//   };

//   useEffect(() => {
//     fetchActivities();
//   }, [refreshKey]); // re-fetch when refreshKey changes

//   return (
//     <Grid container spacing={2}>
//       {activities.map((activity) => (
//         <Grid key={activity.id} item xs={12} sm={6} md={4}>
//           <Card
//             sx={{ cursor: 'pointer', width: '100%' }}
//             onClick={() => navigate(`/activities/${activity.id}`)}
//           >
//             <CardContent>
//               <Typography variant='h6'>{activity.type}</Typography>
//               <Typography>Duration: {activity.duration} minutes</Typography>
//               <Typography>Calories: {activity.caloriesBurned}</Typography>
//             </CardContent>
//           </Card>
//         </Grid>
//       ))}
//     </Grid>
//   );
// };

// export default ActivityList;


import React, { useEffect, useState } from 'react';
import { Grid, Card, CardContent, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { getActivities } from '../services/api';

const ActivityList = ({ refreshKey }) => {
  const [activities, setActivities] = useState([]);
  const navigate = useNavigate();

  const fetchActivities = async () => {
    try {
      const response = await getActivities();
      setActivities(response.data);
    } catch (error) {
      console.error('Error fetching activities:', error);
    }
  };

  useEffect(() => {
    fetchActivities();
  }, [refreshKey]);

  return (
    <Grid container spacing={2}>
      {activities.map((activity) => (
        <Grid key={activity.id} item xs={12} sm={6} md={4}>
          <Card
            sx={{ cursor: 'pointer', width: '100%' }}
            onClick={() => navigate(`/activities/${activity.id}`)}
          >
            <CardContent>
              <Typography variant='h6'>{activity.type}</Typography>
              <Typography>Duration: {activity.duration} minutes</Typography>
              <Typography>Calories: {activity.caloriesBurned}</Typography>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
};

export default ActivityList;

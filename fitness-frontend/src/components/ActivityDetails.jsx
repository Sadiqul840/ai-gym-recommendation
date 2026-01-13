

// import React, { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
// import { getActivityDetails } from '../services/api';
// import { Box, Card, CardContent, Divider, Typography } from '@mui/material';

// const ActivityDetails = () => {
//   const { id } = useParams();
//   const [activity, setActivity] = useState(null);

//   useEffect(() => {
//     const fetchActivityDetails = async () => {
//       try {
//         const response = await getActivityDetails(id);
//         setActivity(response.data); // Ensure response.data is the activity object
//       } catch (error) {
//         console.error('Error fetching activity details:', error);
//       }
//     };

//     fetchActivityDetails();
//   }, [id]);

//   if (!activity) {
//     return <Typography>Loading...</Typography>;
//   }

//   return (
//     <Box sx={{ maxWidth: 800, mx: 'auto', p: 2 }}>
//       <Card sx={{ mb: 2 }}>
//         <CardContent>
//           <Typography variant="h5" gutterBottom>Activity Details</Typography>
//           <Typography>Type: {activity.type}</Typography>
//           <Typography>Duration: {activity.duration} minutes</Typography>
//           <Typography>Calories Burned: {activity.caloriesBurned}</Typography>
//           <Typography>Date: {new Date(activity.createdAt).toLocaleString()}</Typography>
//         </CardContent>
//       </Card>

//       {(activity.recommendation || activity.improvements || activity.suggestions || activity.safety) && (
//         <Card>
//           <CardContent>
//             {activity.recommendation && (
//               <>
//                 <Typography variant="h5" gutterBottom>AI Recommendation</Typography>
//                 <Typography paragraph>{activity.recommendation}</Typography>
//                 <Divider sx={{ my: 2 }} />
//               </>
//             )}

//             {activity.improvements?.length > 0 && (
//               <>
//                 <Typography variant="h6">Improvements</Typography>
//                 {activity.improvements.map((improvement, index) => (
//                   <Typography key={index} paragraph>• {improvement}</Typography>
//                 ))}
//                 <Divider sx={{ my: 2 }} />
//               </>
//             )}

//             {activity.suggestions?.length > 0 && (
//               <>
//                 <Typography variant="h6">Suggestions</Typography>
//                 {activity.suggestions.map((suggestion, index) => (
//                   <Typography key={index} paragraph>• {suggestion}</Typography>
//                 ))}
//                 <Divider sx={{ my: 2 }} />
//               </>
//             )}

//             {activity.safety?.length > 0 && (
//               <>
//                 <Typography variant="h6">Safety Guidelines</Typography>
//                 {activity.safety.map((safety, index) => (
//                   <Typography key={index} paragraph>• {safety}</Typography>
//                 ))}
//               </>
//             )}
//           </CardContent>
//         </Card>
//       )}
//     </Box>
//   );
// };

// export default ActivityDetails;




import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getActivityDetails } from '../services/api';
import { Box, Card, CardContent, Divider, Typography, Button } from '@mui/material';

const ActivityDetails = () => {
  const { id } = useParams();
  const [activity, setActivity] = useState(null);
  const [showFullRecommendation, setShowFullRecommendation] = useState(false);

  useEffect(() => {
    const fetchActivityDetails = async () => {
      try {
        const response = await getActivityDetails(id);
        setActivity(response.data);
      } catch (error) {
        console.error('Error fetching activity details:', error);
      }
    };
    fetchActivityDetails();
  }, [id]);

  if (!activity) return <Typography>Loading...</Typography>;

  const renderList = (items) => (
    <Box sx={{ ml: 2, mb: 2 }}>
      {items.map((item, index) => (
        <Typography key={index} paragraph sx={{ mb: 0.5 }}>
          • {item}
        </Typography>
      ))}
    </Box>
  );

  const toggleRecommendation = () => setShowFullRecommendation(prev => !prev);

  const shortRecommendation = activity.recommendation?.slice(0, 300); // first 300 chars

  return (
    <Box sx={{ maxWidth: 800, mx: 'auto', p: 2 }}>
      <Card sx={{ mb: 3 }}>
        <CardContent>
          <Typography variant="h5" gutterBottom>
            Activity Details
          </Typography>
          <Typography>Type: {activity.type}</Typography>
          <Typography>Duration: {activity.duration} minutes</Typography>
          <Typography>Calories Burned: {activity.caloriesBurned}</Typography>
          <Typography>
            Date: {new Date(activity.createdAt).toLocaleString()}
          </Typography>
        </CardContent>
      </Card>

      {(activity.recommendation || activity.improvements?.length || activity.suggestions?.length || activity.safety?.length) && (
        <Card>
          <CardContent>
            {activity.recommendation && (
              <Box sx={{ mb: 3 }}>
                <Typography variant="h5" gutterBottom>
                  AI Recommendation
                </Typography>
                <Typography paragraph sx={{ whiteSpace: 'pre-line' }}>
                  {showFullRecommendation
                    ? activity.recommendation
                    : shortRecommendation + (activity.recommendation.length > 300 ? "..." : "")}
                </Typography>
                {activity.recommendation.length > 300 && (
                  <Button variant="text" onClick={toggleRecommendation} sx={{ mb: 2 }}>
                    {showFullRecommendation ? "Show Less" : "Read More"}
                  </Button>
                )}
                <Divider sx={{ my: 2 }} />
              </Box>
            )}

            {activity.improvements?.length > 0 && (
              <Box sx={{ mb: 3 }}>
                <Typography variant="h6">Improvements</Typography>
                {renderList(activity.improvements)}
                <Divider sx={{ my: 2 }} />
              </Box>
            )}

            {activity.suggestions?.length > 0 && (
              <Box sx={{ mb: 3 }}>
                <Typography variant="h6">Suggestions</Typography>
                {renderList(activity.suggestions)}
                <Divider sx={{ my: 2 }} />
              </Box>
            )}

            {activity.safety?.length > 0 && (
              <Box sx={{ mb: 3 }}>
                <Typography variant="h6">Safety Guidelines</Typography>
                {renderList(activity.safety)}
              </Box>
            )}
          </CardContent>
        </Card>
      )}
    </Box>
  );
};

export default ActivityDetails;

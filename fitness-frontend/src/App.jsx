



// import { Box, Button, Typography } from "@mui/material";
// import { useContext, useEffect, useState } from "react";
// import { AuthContext } from "react-oauth2-code-pkce";
// import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router-dom";
// import { useDispatch } from "react-redux";
// import { setCredentials } from "./store/authSlice";
// import ActivityForm from "./components/ActivityForm";
// import ActivityList from "./components/ActivityList";
// import ActivityDetails from "./components/ActivityDetails";

// const ActivitiesPage = () => {
//   const [refresh, setRefresh] = useState(false);

//   const handleActivitiesAdded = () => {
//     setRefresh(prev => !prev);
//   };

//   return (
//     <Box sx={{ p: 2, border: '1px dashed grey' }}>
//       <ActivityForm onActivityAdded={handleActivitiesAdded} />
//       <ActivityList key={refresh} />
//     </Box>
//   );
// };

// function App() {
//   const { token, tokenData, logIn, logOut } = useContext(AuthContext);
//   const dispatch = useDispatch();
//   const [authReady, setAuthReady] = useState(false);

//   useEffect(() => {
//     if (token) {
//       dispatch(setCredentials({ token, user: tokenData }));
//       setAuthReady(true);
//     }
//   }, [token, tokenData, dispatch]);

//   return (
//     <Router>
//       {!token ? (
//         <Box
//           sx={{
//             height: "100vh",
//             display: "flex",
//             flexDirection: "column",
//             alignItems: "center",
//             justifyContent: "center",
//             textAlign: "center",
//           }}
//         >
//           <Typography variant="h4" gutterBottom>
//             Welcome to the Fitness Tracker App
//           </Typography>
//           <Typography variant="subtitle1" sx={{ mb: 3 }}>
//             Please login to access your activities
//           </Typography>
//           <Button
//             variant="contained"
//             color="primary"
//             size="large"
//             onClick={logIn}
//           >
//             LOGIN
//           </Button>
//         </Box>
//       ) : (
//         <Box sx={{ p: 2 }}>
//           <Button
//             variant="contained"
//             color="secondary"
//             sx={{ mb: 2 }}
//             onClick={logOut}
//           >
//             LOGOUT
//           </Button>

//           <Routes>
//             <Route path="/" element={<Navigate to="/activities" replace />} />
//             <Route path="/activities" element={<ActivitiesPage />} />
//             <Route path="/activities/:id" element={<ActivityDetails />} />
//           </Routes>
//         </Box>
//       )}
//     </Router>
//   );
// }

// export default App;




import { Box, Button, Typography } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setCredentials } from "./store/authSlice";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetails from "./components/ActivityDetails";

const ActivitiesPage = () => {
  const [refresh, setRefresh] = useState(false);
  const handleActivitiesAdded = () => setRefresh(prev => !prev);

  return (
    <Box sx={{ p: 2, border: '1px dashed grey' }}>
      <ActivityForm onActivityAdded={handleActivitiesAdded} />
      <ActivityList refreshKey={refresh} />
    </Box>
  );
};

function App() {
  const { token, tokenData, logIn, logOut } = useContext(AuthContext);
  const dispatch = useDispatch();

  useEffect(() => {
    if (token) dispatch(setCredentials({ token, user: tokenData }));
  }, [token, tokenData, dispatch]);

  return (
    <Router>
      {!token ? (
        <Box sx={{ height: "100vh", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", textAlign: "center" }}>
          <Typography variant="h4" gutterBottom>Welcome to the Fitness Tracker App</Typography>
          <Typography variant="subtitle1" sx={{ mb: 3 }}>Please login to access your activities</Typography>
          <Button variant="contained" color="primary" size="large" onClick={logIn}>LOGIN</Button>
        </Box>
      ) : (
        <Box sx={{ p: 2 }}>
          <Button variant="contained" color="secondary" sx={{ mb: 2 }} onClick={logOut}>LOGOUT</Button>
          <Routes>
            <Route path="/" element={<Navigate to="/activities" replace />} />
            <Route path="/activities" element={<ActivitiesPage />} />
            <Route path="/activities/:id" element={<ActivityDetails />} />
          </Routes>
        </Box>
      )}
    </Router>
  );
}

export default App;

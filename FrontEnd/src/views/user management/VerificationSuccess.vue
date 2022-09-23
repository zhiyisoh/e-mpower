<template>
    <div class="verification">
        <div class="veri-white" style="text-align:center">
         <h1 class="title">Verification Successful!</h1>
         <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">
         <h3>Congratulations, your account has been successfully verified</h3>
         <p>Click the following button to proceed to login ^O^:</p>
         <RouterLink to="/login"><button type="button" class="btn btn-primary user-btn btn-lg" href="/login">Login</button></RouterLink>
        </div>
    </div>
</template>

  <style>
    .verification {
     padding: 7% 15%;
    text-align: center;
    }

  .veri-white {
  background-color: white;
  margin: 20px 0;
  padding: 7% 15%;
  border-radius: 10%;
  }

  p{
    font-size: 1.2rem;
  }

  </style>
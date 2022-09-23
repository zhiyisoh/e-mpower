<template>
    <div class="verification">
        <div class="veri-white" style="text-align:center">
         <h1 class="title">Forgot Password</h1>
         <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">
         <h4>Please enter your email to reset password</h4>
            <div class="form-group">
                <input type="email" class="form-control" style="width: 75%; display: inline; margin-right: 15px" placeholder="Enter email">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
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
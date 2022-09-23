<template>
    <div class="verification">
        <div class="veri-white" style="text-align:center">
         <h1 class="title">Reset Password</h1>
         <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">
         <h4>Please enter your username/email to reset password</h4>
            <div class="form-group">
                <input type="password" class="form-control fields" placeholder="New Password">
                <input type="password" class="form-control fields" placeholder="Retype New Password">
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

  .fields{
    margin-bottom: 20px;
  }

  p{
    font-size: 1.2rem;
  }



  </style>
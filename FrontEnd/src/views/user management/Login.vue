<template>
    <div class="login-view">
      <form>
        <div class="form-group">
          <h2>Login</h2><br>
          <label for="username">Username:</label><br>
          <input type="text" id="username" name="username" required><br>
          <label for="pswd">Password:</label><br>
          <input type="password" id="pswd" name="pswd" required><br>
          </div>
      </form>
      <br>
      <div>
        <button class="btn" type="submit">Login</button>
        <br>
        <button type="button" class="google"><input id="google-icon" width="50" height="50" type="image" src="/src/assets/googleicon.png" />Login with Google</button>
      </div>
      <div class="to-register">
        <p>Don't have an account? 
        <RouterLink to="/register"><button type="button" class="link" href="Register.vue"><u>REGISTER</u></button></RouterLink>
      </p>
      </div>
    </div>
  </template>
  
  <script>
    export default {
        data() {
            return {}
        }
    }
</script>

  <style>
    h2,p{
      font-family: 'Merriweather', sans-serif;
      color:#5E454B;
      padding-top:50px;
    }
    
    .to-register{
      padding-top:10px;
      padding-bottom:20px;
      font-family: 'Merriweather', sans-serif;
      color:#5E454B;
      margin-left: 100px;
      display:inline-block;
    }
    .link{
      border-color: transparent;
      background-color: transparent;
      color:#5E454B;
      display:inline;
    }
    .login-view{
      background-color: whitesmoke;
      margin-top: 20px;
      border-radius:8px;
    }

    .form-group{
      font-family: Verdana, Geneva, Tahoma, sans-serif;
      margin-left: 100px;
      margin-bottom:5px;
      padding-top:20px;
    }

    img{
      max-width: 100%;
      padding-right:7px;
    }

    .google{
      background-color: aliceblue;
      color: black;
      margin-left: 100px;
      margin-bottom:5px;
      border-radius: 8px;
      text-align: center;
      align-items: center;
      font-size: 20px;
      height:53px;
      display:inline-flex;
      border-color:transparent;
    }

  @media (min-width: 1024px) {
    .about {
      min-height: 100vh;
      display: flex;
      align-items: center;
    }
  }
  </style>
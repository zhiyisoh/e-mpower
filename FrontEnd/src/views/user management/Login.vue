<script setup>
  import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="login-view">
      <Form @submit="handleLogin" :validation-schema="schema">
        
        <h2 class="header">Login</h2><br>
        <div class="form-group">
            <label for="username">Username</label>
            <Field name="username" type="text" class="form-control" />
            <ErrorMessage name="username" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <Field name="password" type="password" class="form-control" />
            <ErrorMessage name="password" class="error-feedback" />
          </div>
  
          <div class="form-group">
            <div v-if="message" class="alert alert-danger" role="alert">
              {{ message }}
            </div>
          </div>
      </Form>
      <br>
      <div>
        <button class="btn" type="submit" :disabled="loading">
          <span v-show="loading" class="spinner-border spinner-border-sm"></span>
          Login
        </button>
        
        <br>
        <button type="button" class="google"><input id="google-icon" width="50" height="50" type="image" src="/src/assets/googleicon.png" />Login with Google</button>
      </div>
      <div class="to-register">
        <p>Don't have an account? 
        <RouterLink to="/register"><button type="button" class="link" href="Register.vue"><u>REGISTER</u></button></RouterLink>
      </p>
      </div>
    </div>
    <Footer/>
  </template>
  

<script>
  import { Form, Field, ErrorMessage } from "vee-validate";
  import * as yup from "yup";
  
  export default {
    name: "Login",
    components: {
      Form,
      Field,
      ErrorMessage,
    },
    data() {
      const schema = yup.object().shape({
        username: yup.string().required("Username is a required field."),
        password: yup.string().required("Password is a required field."),
      });
  
      return {
        loading: false,
        message: "",
        schema,
      };
    },
    computed: {
      loggedIn() {
        return this.$store.state.auth.status.loggedIn;
      },
    },
    created() {
      if (this.loggedIn) {
        this.$router.push("/home"); 
      }
    },
    methods: {
      handleLogin(user) {
        this.loading = true;
  
        this.$store.dispatch("auth/login", user).then(
          () => {
            this.$router.push("/home");
          },
          (error) => {
            this.loading = false;
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
          }
        );
      },
    },
  };
  </script>

  <style>
    h2,p{
      font-family: 'Merriweather', sans-serif;
      color:#5E454B;
      padding-top:50px;
    }
    .header{
      padding-left: 100px;
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
      width: 500px;
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

    .profile-img-card {
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    display: block;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
  }
  
  .error-feedback {
    color: red;
  }

  @media (min-width: 1024px) {
    .about {
      min-height: 100vh;
      display: flex;
      align-items: center;
    }
  }
  </style>
<script setup>
  import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="login-view">
      <Form @submit="Login" :validation-schema="schema">
        
        <h2 class="header">Login</h2><br>
        <div class="form-group">
            <label for="username">Username</label>
            <Field name="username" type="text" class="form-control" />
            <ErrorMessage name="username" class="error-message" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <Field name="password" type="password" class="form-control" />
            <ErrorMessage name="password" class="error-message" />
          </div>
          <RouterLink to="/forgotPassword"><button class="forgot-password link ">Forgot Password?</button></RouterLink>
          
  
          <div class="form-group">
            <div v-if="message" class="alert alert-danger" role="alert">
              {{ message }}
            </div>
          </div>

      </Form>
      <br>
      <div>
        <button class="btn login-btn" :disabled="loading">
          <span v-show="loading" class="spinner-border spinner-border-sm"></span>
          Login
        </button>
        
        <br>

        <p>or</p>
        <button type="button" class="google"><input id="google-icon" width="50" height="50" type="image" src="/src/assets/googleicon.png" />Login with Google</button>
      </div>

      <div class="to-register">
        <p>Don't have an account? 
        <RouterLink to="/register"><button type="button" class="link" href="Register.vue"><u>Click here to register.</u></button></RouterLink>
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
      Login(user) {
        this.loading = true;
        
        // successful login
        this.$store.dispatch("auth/login", user).then(
          () => {
            this.$router.push("/home");
          },

        //unsuccessful login
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

    .header {
      font-weight: 700;
    }

    .to-register{
      padding-top:10px;
      padding-bottom:20px;
      font-family: 'Merriweather', sans-serif;
      color:#5E454B;
      display:inline-block;
    }

    .link{
      border-color: transparent;
      background-color: transparent;
      color:#5E454B;
      display:inline;
    }

    .login-view{
      background-color: white;
      margin: 20px auto;
      text-align: center;
      border-radius: 10%;
      width: 50%;
    }

    .form-group{
      margin: auto;
      padding-top: 20px;
      width: 500px;
    }

    .forgot-password{
      margin: 2px;
    }

    .login-btn {
      background-color: #5E454B;
      color: white;
      margin: 10px 0;
      font-size: 20px;
      padding: 8px;
    }
    
    .error-message {
      color: red;
      margin: 2px;
    }
    
    .google {
      background-color: rgb(255, 255, 255);
      color: black;
      margin-bottom: 5px;
      border-radius: 8px;
      text-align: center;
      align-items: center;
      font-size: 18px;
      height: 53px;
      display: inline-flex;
      border-color: transparent;
    }
    
    .google:hover {
      border-color: grey;
    }


  </style>
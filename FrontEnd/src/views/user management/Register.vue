<script setup>
  import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="register-view">
      <Form @submit="handleLogin" :validation-schema="schema">
        
        <h2 class="header">Register</h2><br>
        <div class="form-group">
            <label for="username">Username</label>
            <Field name="username" type="text" class="form-control" />
            <ErrorMessage name="username" class="error-feedback" />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <Field name="email" type="email" class="form-control" />
          <ErrorMessage name="email" class="error-feedback" />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <Field name="password" type="password" class="form-control" />
          <ErrorMessage name="password" class="error-feedback" />
        </div>
        <div class="form-group">
          <label for="password">Confirm password</label>
          <Field name="confirm-password" type="password" class="form-control" />
          <ErrorMessage name="confirm-password" class="error-feedback" />
        </div>
          <div class="form-group">
            <div v-if="message" class="alert alert-danger" role="alert">
              {{ message }}
            </div>
          </div>
      </Form>
      
      <div>
        <button class="btn" type="submit" :disabled="loading">
          <span
             v-show="loading"
             class="spinner-border spinner-border-sm"></span>
          Register
        </button>
        <br>
        <button type="button" class="google"><input id="google-icon" width="50" height="50" type="image" src="/src/assets/googleicon.png" />Register with Google</button>
      </div>
      <div class="to-login">
        <p>Already have an account? 
        <RouterLink to="/login"><button type="button" class="link" href="Login.vue"><u>LOGIN</u></button></RouterLink>
      </p>
      </div>
    </div>
    <Footer/>
  </template>
  
  <script>
    import { Form, Field, ErrorMessage } from "vee-validate";
    import * as yup from "yup";
    
    export default {
      name: "Register",
      components: {
        Form,
        Field,
        ErrorMessage,
      },
      data() {
        const schema = yup.object().shape({
          username: yup
            .string()
            .required("Username is required!")
            .min(3, "Must be at least 3 characters!")
            .max(20, "Must be maximum 20 characters!"),
          email: yup
            .string()
            .required("Email is required!")
            .email("Email is invalid!")
            .max(50, "Must be maximum 50 characters!"),
          password: yup
            .string()
            .required("Password is required!")
            .min(6, "Must be at least 6 characters!")
            .max(40, "Must be maximum 40 characters!"),
        });
    
        return {
          successful: false,
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
      mounted() {
        if (this.loggedIn) {
          this.$router.push("/profile");
        }
      },
      methods: {
        handleRegister(user) {
          this.message = "";
          this.successful = false;
          this.loading = true;
    
          this.$store.dispatch("auth/register", user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
              this.loading = false;
            },
            (error) => {
              this.message =
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();
              this.successful = false;
              this.loading = false;
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
    .to-login{
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
    .register-view{
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

    .btn{
      background-color:  #5E454B;
      color: honeydew;
      margin-left: 100px;
      margin-bottom:10px;
      font-size: 20px;
      padding: 8px;
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
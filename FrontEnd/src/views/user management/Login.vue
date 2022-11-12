<script setup>
import Footer from "../../components/Footer.vue";
</script>
<template>
  <div class="login-view">
    <div class="col-md-12">
      <div class="card card-container">
        <h2 class="header">Login</h2>
        <Form @submit="handleLogin" :validation-schema="schema">
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
          <RouterLink to="/forgotPassword"><button class="forgot-password link ">Forgot Password?</button></RouterLink>

          <div class="form-group">
            <button class="btn btn-primary btn-block" :disabled="loading">
              <span v-show="loading" class="spinner-border spinner-border-sm"></span>
              <span>Login</span>
            </button>
          </div>
          <div class="form-group">
            <div v-if="message" class="alert alert-danger" role="alert">
              {{ message }}
            </div>
          </div>
          <div class="to-register">
            <p>Don't have an account?
              <RouterLink to="/register"><button type="button" class="link" href="Register.vue"><u>Click here to
                    register.</u></button></RouterLink>
            </p>
          </div>
        </Form>
      </div>
    </div>
  </div>
  <Footer />
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
    //  this.$store.state.auth allows you to get the status 
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      //redirect to /home screen
      this.$router.push("/home");
    }
  },
  methods: {
    handleLogin(user) {
      this.loading = true;

      // successful login: make request by dispatching action
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

<style scoped>
h2,
p {
  font-family: 'Merriweather', sans-serif;
  color: #5E454B;
  padding-top: 50px;
}

a{
  text-decoration: none;
}

.header {
  font-weight: 700;
}

.to-login {
  padding-top: 10px;
  padding-bottom: 20px;
  font-family: 'Merriweather', sans-serif;
  color: #5E454B;
  display: inline-block;
}

.link {
  border-color: transparent;
  background-color: transparent;
  color: #5E454B;
  display: inline;
}

.login-view {
  margin: 20px auto;
  text-align: center;
  width: 50%;
  padding: 2%;
}

.card-container {
  border-color: transparent;
  margin: 0 auto;
  background-color: white;
  background-color: white;
  border-radius: 10%;
}

.form-group {
  margin: auto;
  padding-top: 20px;
  width: 90%;
}
.error-feedback {
  color: red;
  margin: 2px;
}

.forgot-password {
  position: relative;
  left: 170px;
}

.google {
  background-color: rgb(255, 255, 255);
  color: black;
  margin: 0 auto;
  margin-bottom: 5px;
  border-radius: 8px;
  text-align: center;
  align-items: center;
  font-size: 15px;
  height: 53px;
  display: inline-flex;
  border-color: transparent;
  width: 250px;
}

#google-icon {
  margin-right: 10px;
}

.google:hover {
  border-color: grey;
}

@media(max-width: 960px){
  .login-view{
    width: 90%;
    border-radius: 5%;
  }

  .forgot-password {
    position: static;
  }
}
</style>
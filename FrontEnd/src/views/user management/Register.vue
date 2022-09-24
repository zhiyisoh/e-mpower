<script setup>
import Footer from "../../components/Footer.vue";
</script>

<template>
  <div class="register-view">
    <Form class="registerForm" @submit="Registration" :validation-schema="schema">

      <h2 class="header">Register</h2><br>

      <!-- USERNAME FIELD -->
      <div class="form-group">
        <label for="username">Username</label>
        <Field name="username" type="text" class="form-control" />
        <ErrorMessage name="username" class="error-message" />
      </div>

      <!-- EMAIL FIELD -->
      <div class="form-group">
        <label for="email">Email</label>
        <Field name="email" type="email" class="form-control" />
        <ErrorMessage name="email" class="error-message" />
      </div>

      <!-- PASSWORD FIELD -->
      <div class="form-group">
        <label for="password">Password</label>
        <Field name="password" type="password" class="form-control" />
        <ErrorMessage name="password" class="error-message" />
      </div>

      <!-- CONFIRM PASSWORD FIELD -->
      <div class="form-group">
        <label for="confirmpassword">Confirm Password</label>
        <Field name="confirm-password" type="password" class="form-control" />
        <ErrorMessage name="confirm-password" class="error-message" />
      </div>

      <!-- ANY ALERT -->
      <div class="form-group">
        <div v-if="message" class="alert alert-danger" role="alert">
          {{ message }}
        </div>
      </div>

    </Form>

    <div>
      <button class="btn register-btn" :disabled="loading">
        <span v-show="loading" class="spinner-border spinner-border-sm"></span>
        Register
      </button>
      <br>

      <p>or</p>
      <button type="button" class="google"><input id="google-icon" width="50" height="50" type="image"
          src="/src/assets/googleicon.png" />Register with Google</button>
    </div>
    
    <div class="to-login">
      <p>Already have an account?
        <RouterLink to="/login"><button type="button" class="link" href="Login.vue"><u>Click to login here.</u></button>
        </RouterLink>
      </p>
    </div>
  </div>
  <Footer />
</template>
  
<script>

// Using vee validate and yup for form
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

    // USING YUP TO LIMIT WHAT INPUT CAN BE TYPED

    const schema = yup.object().shape({
      username: yup
        .string()
        .required("Username is a required field.")
        .min(8, "Username must be at least 8 characters.")
        .max(25, "Username must be at most 25 characters."),
      email: yup
        .string()
        .required("Email is a required field.")
        .email("Email is invalid. Please key in with the correct format."),
      password: yup
        .string()
        .required("Password is a required field.")
        .min(8, "Password must be at least 8 characters.")
        .max(30, "Password must be at most 30 characters."),
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
    Registration(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;

      // successful registration
      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
        },

        // unsuccessful registration
        (error) => {
          this.message =
            (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
  },
};
</script>

<style>
h2,
p {
  font-family: 'Merriweather', sans-serif;
  color: #5E454B;
  padding-top: 50px;
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

.register-view {
  background-color: rgb(255, 255, 255);
  margin: 20px auto;
  text-align: center;
  border-radius: 10%;
  width: 50%;
}

.form-group {
  margin: auto;
  padding-top: 20px;
  width: 500px;
}

.register-btn {
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
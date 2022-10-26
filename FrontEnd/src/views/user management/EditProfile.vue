<script setup>
import Footer from "../../components/Footer.vue";
</script>

<template>
  <div class="editprofile-view">

    <div class="col-md-12">
      <div class="card card-container">
        <h2 class="header">Edit Profile</h2>
        <Form>
          <div v-if="!successful">
            <div class="form-group">
              <label for="username">Username</label>
              <Field name="username" type="text" class="form-control">
                <input  v-model="username" type="text" class="form-control"/>
              </Field>
              <ErrorMessage name="username" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <Field name="email" type="email" class="form-control">
                <input  v-model="email" type="text" class="form-control"/>
              </Field>
              <ErrorMessage name="email" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <Field name="password" type="password" class="form-control" />
              <ErrorMessage name="password" class="error-feedback" />
            </div>

            <div class="form-group">
              <label for="confirmpassword">Confirm Password</label>
              <Field name="confirmpassword" type="password" class="form-control" />
              <ErrorMessage name="confirmpassword" class="error-feedback" />
            </div>

            <div class="form-group">
              <button class="btn btn-primary btn-block" :disabled="loading">
                <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                Register
              </button>
            </div>
          </div>

          <div v-if="message" class="alert alertmsg" :class="successful ? 'alert-success' : 'alert-danger'">
            {{ message }}
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
  name: "EditProfile",
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
        .max(25, "Must be maximum 25 characters!"),
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!"),
      password: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(30, "Must be maximum 30 characters!"),
      confirmpassword: yup
        .string()
        .required("Confirm Password Field is required!")
        .oneOf([yup.ref('password'), null], 'Passwords must match')
    });

    return {
      username: this.$store.state.auth.user.username,
      email: this.$store.state.auth.user.email
    }

    
  }

  
      
}
</script>


<style scoped>
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

.editprofile-view {
  margin: 20px auto;
  text-align: center;
  width: 50%;
  padding: 2%;
}

.card-container {
  border-color: transparent;
  margin: 0 auto;
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

.alertmsg{
  margin: 10px 15px;
}

@media(max-width: 960px){
  .register-view{
    width: 90%;
    border-radius: 5%;
  }
}
</style>
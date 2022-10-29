<script setup>
import Footer from "../../components/Footer.vue";
</script>

<template>
  <div class="editprofile-view">

    <div class="col-md-12">
      <div class="card card-container">
        <h2 class="header">Edit Profile</h2>
        <Form @submit="onSubmit" :validation-schema="schema"  >
          <div>
            <div class="form-group">
              <label for="username">Username</label>
                <Field name="usernameCheck" v-model="record.username" type="text" class="form-control"/>
              <ErrorMessage name="usernameCheck" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="email">Email</label>
                <Field name="emailCheck" v-model="record.email" type="text" class="form-control"/>
              <ErrorMessage name="emailCheck" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
                <Field name="passwordCheck" v-model="password" type="password" class="form-control"/>
              <ErrorMessage name="passwordCheck" class="error-feedback" />
            </div>

            <div class="form-group">
              <label for="confirmpassword">Confirm Password</label>
                <Field name="confirmpasswordCheck" v-model="confirmpassword" type="password" class="form-control"/>
              <ErrorMessage name="confirmpasswordCheck" class="error-feedback" />
            </div>

            <div class="form-group">
              <button class="btn btn-primary btn-block" :disabled="loading">
                <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                Edit
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
import axios from 'axios';

const schema = yup.object().shape({
      usernameCheck: yup
        .string()
        .required("Username is required!")
        .min(3, "Must be at least 3 characters!")
        .max(25, "Must be maximum 25 characters!"),
      emailCheck: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!"),
      passwordCheck: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(30, "Must be maximum 30 characters!"),
      confirmpasswordCheck: yup
        .string()
        .required("Confirm Password Field is required!")
        .oneOf([yup.ref('passwordCheck'), null], 'Passwords must match')
    });

export default {
  name: "EditProfile",  
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  el: "#app",
  data() {

    return {
      username: "",
      email: "",
      password: "",
      confirmpassword: "",
      successful: false,
      loading: false,
      message: "",
      schema,
      record: []
    }
    
  }, created() {
    const url = "http://localhost:8080/api/auth/profile/" ;
    axios.get(url + this.$store.state.auth.user.id, {
      headers: {
        'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
      }
    })
      .then(response => {
        this.record = response.data;
        console.log(response.data);

      }).catch((error) => {
        this.error = "Error!  " + error;
      });


  }
  , methods: {
    onSubmit(e) {
      this.successful = true;
      this.loading = false;

      let currentObj = this;
      const API_URL ='http://localhost:8080/api/auth/editprofile/' + this.$store.state.auth.user.id;

      axios.put(API_URL, {
        username: this.record.username,
        email: this.record.email,
        password: this.password
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken 
        }
      })
        .then(function (response) {
          currentObj.output = response.data;
          alert("Your account details have been saved.");
          history.back();
        })
        .catch(function (error) {
          currentObj.output = error;
          alert('Unsuccessful Submission. ' + error);
          
        });
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

.btn{
  margin-bottom: 20px;
}
.alertmsg{
  margin: 10px 15px;
}

</style>
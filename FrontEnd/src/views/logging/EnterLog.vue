<script setup>
  import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="enter-log-view">
      <RouterLink to="/log"><button type="button" class="log-link" href="Log.vue">Back</button></RouterLink>
      <h1>Hello {{this.$store.state.auth.user.username}}, ready to recycle?</h1>
      
      <div class="enter">
        <Form @submit="" :validation-schema="schema">
          <div class="form-group">
            <label for="type">Type of e-waste recyling: </label>
            <Field name="type" type="text" class="form-control" />
          </div>
          <div class="form-group">
            <label for="date">Date (YYYY-MM-DD): </label>
            <Field name="date" type="text" class="form-control" />
          </div>
          <div class="form-group">
            <label for="notes">Notes: </label>
            <Field name="notes" type="text" class="form-control" />
          </div>

        </Form>
      </div>
    
    <Footer />
    </div>
    <!-- <input type="file" accept="image/*" @change="uploadImage($event)" id="file-input"> -->
    <!-- code to allow user to upload image -->
    <!-- <v-file-input
      v-model="file"
      chips
      accept="image/*"
      label="Image"
    /> -->
    <input type="file" accept="image/*" @change="onChange" />
    <div id="preview">
      <img v-if="item.imageUrl" :src="item.imageUrl" />
    </div>


</template>
  
<!-- <script>
methods: {
  method to upload image, still figuring it out
  uploadImage(event) {

    const URL = 'http://foobar.com/upload';

    let data = new FormData();
    data.append('name', 'my-picture');
    data.append('file', event.target.files[0]);

    let config = {
      header: {
        'Content-Type': 'image/png'
      }
    }

    axios.put(
      URL,
      data,
      config
    ).then(
      response => {
        console.log('image upload response > ', response)
      }
    )
  }
}
</script> -->

<!-- <script>
  export default{
    data:{file: null, image:null},
    methods: {
      onFileChange(){
        const reader= new FileReader()
        reader.readAsDataURL(this.file)
        reader.onoload = e => {
          this.image = e.target.result
          console.log(this.image)
        }
      }
    }

  }
</script> -->
<script>
  export default {
    name: 'imageUpload',
    data() {
      return {
        item:{
            //...
            image : null,
            imageUrl: null
        }
      }
    },
    methods: {
      onChange(e) {
        const file = e.target.files[0]
        this.image = file
        this.item.imageUrl = URL.createObjectURL(file)
      }
    }
  } 
  </script>

<style>
  h1{
    text-align: center;
  }
  .enter-log-view{
    font-family: 'Merriweather', sans-serif;
    color: #5E454B;
  }

  .form-group{
    font-size: 25px;
    margin: auto;
    padding-top: 20px;
    width: 90%;
    height:70%;
  }

  .form-control{
    height:35px;
  }
</style>
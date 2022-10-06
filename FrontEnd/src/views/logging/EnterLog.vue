<script setup>
  import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="enter-log-view">
      <form>
        <input class="back" type="button" value="Back to past logs" onclick="history.back()">
      </form>
      <h1>Hello {{this.$store.state.auth.user.username}}, ready to recycle?</h1>
      
      <div class="enter">
        <Form @submit="" :validation-schema="schema">
          <div class="form-group">
            <label for="name">Item name: </label>
            <input name="name" type="text" class="form-control" />
          </div>
          <div class="form-group">
            <label for="type">Type of e-waste recyling: </label>
            <input name="type" type="text" class="form-control" />
          </div>
          <div class="form-group">
            <label for="date">Date (YYYY-MM-DD): </label>
            <input name="date" type="text" class="form-control" />
          </div>
          <div class="form-group">
            <label for="notes">Notes: </label>
            <input name="notes" type="text" class="form-control" />
          </div>
          <div class="form-group">
            <div id="preview">
              <input type="file" accept="image/*" @change="onChange" />
              <img v-if="item.imageUrl" :src="item.imageUrl" />
            </div>
        </div>
        <div class="form-group">
              <button class="btn btn-primary btn-block" :disabled="loading">
                <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                Submit
              </button>
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

<script>
  export default {
    name: 'imageUpload',
    data() {
      return {
        item:{
            type : '',
            date : '',
            notes : '',
            image : null,
            imageUrl: null
        }
      }
    },
    methods: {
      onSubmit(e){
        e.preventDefault()
        if(!this.type){
          alert('Please enter type of e-waste')
          return
        }
        const NewInformation = {
            id: Math.floor(Math.random() * 100000),
            name : this.name,
            //age : this.age,
            //reminder : this.reminder
        }
        this.$emit('add-information', newInformation)
        this.name = ' '
        //this.age = ' '

      }

      // onChange(e) { -- check how to add 2 methods
      //   const file = e.target.files[0]
      //   this.image = file
      //   this.item.imageUrl = URL.createObjectURL(file)
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
  .back{
    background-color: #5E454B;
    font-family: 'Merriweather', sans-serif;
    color: white;
    border-radius: 8px;
    border-color: transparent;
    margin-left:40px;
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
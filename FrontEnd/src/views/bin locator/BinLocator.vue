<script setup>
    import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="locator-view">
        <div class="form-container">
            <div class="form-header">
                <h1>Locate a Bin!</h1>
                <br>
                <p class="desc">With E-MPOWER's Bin Locator feature, 
                you can find the nearest available bin to recycle your e-waste. 
                <br><strong class="impt">IMPORTANT:</strong> If your item type is not in the options,
                 you are <strong>NOT</strong> able to recycle via e-waste recycling bins. Click <a href="">here</a> for more information</p>
                
            </div>
            
            <div class="form-locate">
                <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
                    <div class="form-group">
                        <label for="location">Postal Code / Address</label>
                        <input name="location" v-model="itemName" type="text" class="form-control" />
                        <button type="button" class="btn btn-primary saved-locations-btn">View Saved Locations</button>
                        <ErrorMessage name="username" class="error-feedback" />
                        
                    </div>

                    <div class="form-group name-field">
                        <label for="itemName">Item Type:</label>
                        
                      </div>
                </Form>
            </div>
        </div>
    </div>
    
    
    <Footer/>
</template>

<script>

</script>
<style scoped>

h1 {
    font-weight: 700;
}

label {
    margin: 7px 5px 0;
    font-size: 20px;
    
 
  }
  
    .name-field {
        margin-top: 60px;
    }
    .locator-view {
        text-align: center;
    }

    .desc {
        width: 80%;
        margin: 0 auto;
    }

    .impt {
        color: red;
        
    }
    .form-locate {
        width: 700px;
        margin: 30px auto;
        
    }

    .saved-locations-btn {
        margin-top: 10px;
        float: right;
    }
</style>
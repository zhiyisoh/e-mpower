<script setup>
    import Footer from "../../components/Footer.vue";
    import BinCard from "../../components/BinCard.vue";
</script>

<template>
    <div class="container">
        <p>Edit the details of the address below.</p>
    </div>

    <div class="formBin">
        <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
            <div class="form-group">
                <label for="binAddress">Enter New Address</label>
                <input name="binAddress" v-model="binAddress" type="text" class="form-control" />

                <label for="postalCode">Enter New Postal code</label>
                <input name="postalCode" v-model="postalCode" type="text" class="form-control" />

                <label for="ICTRecycling">Recycles ICT?</label>
                <input name="ICTRecycling" v-model="ICTRecycling" type="radio" id="one" value="One"  />
                <label for="one">True</label>

                <input name="ICTRecycling" v-model="ICTRecycling" type="radio" id="two" value="Two" />
                <label for="two">False</label>
            </div>

            <div class="form-group">
                <button class="btn btn-primary btn-block" :disabled="loading">
                    <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                    Submit
                </button>
            </div>
        </Form>
    </div>
    <BinCard/>

    <Footer/>

</template>

<style scoped>
    h1 {
        font-weight: 700;
    }

    .container {
        text-align: center;
    }

    label {
    margin: 7px 5px 0;
    font-size: 20px;
  }

</style>
package spring

import com.ttn.SpringSecurity.UserPasswordEncoderListener



// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))

   }

# Travel agency app

## App overveiw
The app is a Spring Boot software application prototype for a travel company. The home page provides two carousels, one shows the most booked trips and the second the ones that
have a discount. By clicking on the cards the user is provided with details about the trip and an option to book. If the user is logged in they can procceed to booking the trip.
First they need to provide a phone number for their reservation and a comment (optional). From there they are redirected to a payment page, where the user can
choose a payment method and fill in the form. If goes well the trip is booked and the user can see it in thei `My trips` section with all previously booked trips.
Moderators can update the content on the platform and admins can change the roles of other users. More details below. There is a scheduled cron event that occurs each day at
9:55 EET, which is sending an email to all registered users (currently it is sending an email only to my email to avoid spam),

* The application supports 3 authorized roles as well as guest user:

    Roles:
    * ADMIN
    * MODERATOR
    * STANDARD

## Guest
    A guest user can see the trips on the platform, read the details for them and use the search functionality.

## STANDARD
    STANDARD users are all users that registered in the application and who don't have admin or moderator roles.

    STANDARD users are granted all the rights a guest user has,
    as well as access to the booking and my-trips features.
    STANDARD users can book the trips they like by completing the payment proccess.
    After which they will see their all booked trips on the `my trips` page.
    
## MODERATOR
    MODERATOR users are granted all the rights a STANDARD user has,
    as well as access to creating and editing features.
    MODERATOR users can create new trips and edit existing ones.
    
    ! IMPORTANT
    If you want to test the moderator funcionallity
    use the following credentials:
    email: moderator@example.com
    password: DUMMY_PASS

## Admin
    The admin is hard coded in the database,
    so no user can register with a admin role.

    The admin has access to all the feature a STANDARD user and a MODERATOR have. So, admins can book trips, edit content, but they can
    also change the roles of all users on the platform, besides other admins. To access that use the `Admin dashboard`

    ! IMPORTANT
    If you want to test the admin funcionallity
    use the following credentials:
    email: admin@example.com
    password: DUMMY_PASS


Everyone is free to fork the repo, only keep in mind that the `ampplication.properties` file is missing valuable information for the app, but is not public for security reasons,
so some errors will occure when you start the project. Contact myself for details.

The app uses:
Stripe for payment
AWS s3 fo uploading images
Thymeleaf as a template engine
Spring security for authorization and authentication
MySQL connector and JPA for using a mysql database
spring-boot-starter-mail for sending emails

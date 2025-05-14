package config;

public class session {

    private static session instance;

    private String u_id;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String username;
    private String acc_type;
    private String acc_status;
    private String acc_pfp;
    private String secQ;
    private String secQanswer;
    private String currentOtp;

    private session() {
        // Private constructor to prevent instantiation
    }

    public static synchronized session getInstance() {
        if (instance == null) {
            instance = new session();
        }
        return instance;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getAcc_status() {
        return acc_status;
    }

    public void setAcc_status(String acc_status) {
        this.acc_status = acc_status;
    }

    public String getAcc_pfp() {
        return acc_pfp;
    }

    public void setAcc_pfp(String acc_pfp) {
        this.acc_pfp = acc_pfp;
    }

    public String getSecQ() {
        return secQ;
    }

    public void setSecQ(String secQ) {
        this.secQ = secQ;
    }

    public String getSecQanswer() {
        return secQanswer;
    }

    public void setSecQanswer(String secQanswer) {
        this.secQanswer = secQanswer;
    }

    public String getCurrentOtp() {
        return currentOtp;
    }

    public void setCurrentOtp(String currentOtp) {
        this.currentOtp = currentOtp;
    }
}
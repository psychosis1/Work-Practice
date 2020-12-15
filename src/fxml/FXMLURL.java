package fxml;


import java.net.URL;

public class FXMLURL {
    public URL getGeneral(){
        return getClass().getResource("general.fxml");
    }

    public URL getLogin(){
        return getClass().getResource("login.fxml");
    }

    public URL getAddUser(){
        return getClass().getResource("addUser.fxml");
    }

    public URL getClient(){
        return getClass().getResource("client.fxml");
    }

    public URL getClientForm(){
        return getClass().getResource("clientForm.fxml");
    }

    public URL getProfile(){
        return getClass().getResource("profile.fxml");
    }

    public URL getRefactorUser(){
        return getClass().getResource("refactorUser.fxml");
    }

    public URL getTestBoyko(){
        return getClass().getResource("testBoyko.fxml");
    }

    public URL getTestGAGE(){
        return getClass().getResource("testGAGE.fxml");
    }

    public URL getTypologicalGroup(){
        return getClass().getResource("typologicalGroup.fxml");
    }

    public URL getUserList(){
        return getClass().getResource("userList.fxml");
    }

}

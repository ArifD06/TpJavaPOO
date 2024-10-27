public class ClientNonAutoriseException extends RuntimeException {
  public ClientNonAutoriseException(String message) {
    super(message);
  }
}

class VehiculeIndisponibleException extends RuntimeException{
  public VehiculeIndisponibleException(String message){
    super(message);
    return;
  }
}
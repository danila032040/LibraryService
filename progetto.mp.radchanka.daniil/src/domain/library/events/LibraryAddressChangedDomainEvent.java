package domain.library.events;

import base.ddd.DomainEvent;
import domain.common.Address;
import domain.library.Library;

public class LibraryAddressChangedDomainEvent implements DomainEvent {
    private final Library library;
    private final Address previousAddress;
    private final Address currentAddress;
    
    public LibraryAddressChangedDomainEvent(Library library, Address previousAddress, Address currentAddress) {
        this.library = library;
        this.previousAddress = previousAddress;
        this.currentAddress = currentAddress;
    }
    
    public Address getCurrentAddress() {
        return currentAddress;
    }
    
    public Library getLibrary() {
        return library;
    }
    
    public Address getPreviousAddress() {
        return previousAddress;
    }
    
}

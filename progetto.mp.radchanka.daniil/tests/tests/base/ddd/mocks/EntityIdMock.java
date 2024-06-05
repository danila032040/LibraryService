package tests.base.ddd.mocks;

import java.util.Objects;

public class EntityIdMock {
    private final int identificationNumder;
    
    public EntityIdMock(int id) {
        this.identificationNumder = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityIdMock)) {
            return false;
        }
        EntityIdMock other = (EntityIdMock) obj;
        return identificationNumder == other.identificationNumder;
    }
    
    public int getIdentificationNumber() {
        return identificationNumder;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(identificationNumder);
    }
}

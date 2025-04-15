/* SPDX-License-Identifier: Apache-2.0 */
package hu.bme.mit.ftsrg.chaincode.launchcodes.contracts;

import static hu.bme.mit.ftsrg.chaincode.launchcodes.util.Serializer.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import hu.bme.mit.ftsrg.chaincode.launchcodes.assets.AssetBase;
import hu.bme.mit.ftsrg.chaincode.launchcodes.assets.Card;
import hu.bme.mit.ftsrg.chaincode.launchcodes.assets.CardType;
import hu.bme.mit.ftsrg.chaincode.launchcodes.events.CloseDoorEvent;
import hu.bme.mit.ftsrg.chaincode.launchcodes.events.OpenDoorEvent;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.CompositeKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
final class LaunchCodesTest {

  LaunchCodes contract;
  @Mock private Context ctx;
  @Mock private ChaincodeStub stub;

  private String toJson(Object obj) {
    return serialize(obj);
  }

  private byte[] toBytes(Object obj) {
    return toJson(obj).getBytes(java.nio.charset.StandardCharsets.UTF_8);
  }

  // utility method, you can create similar ones for other operations
  void createdExactlyOnceInStub(AssetBase newAsset) {
    String keyString =
        new CompositeKey(newAsset.getTypeForCompositeKey(), newAsset.getAttributesForCompositeKey())
            .toString();
    
    // allow multiple reads, if no caching is implemented
    verify(stub, atLeastOnce()).getStringState(eq(keyString));
    // it didn't exist before
    assertThat(stub.getStringState(keyString)).isEqualTo("");
    // we've written it exatly once
    verify(stub, times(1)).putStringState(eq(keyString), eq(toJson(newAsset)));
  }

  void closeDoorEventFiredOnceInStub(CloseDoorEvent firedEvent) {
    verify(stub, times(1)).setEvent(eq(CloseDoorEvent.class.getName()), eq(toBytes(firedEvent)));
  }

  void openDoorEventFiredOnceInStub(OpenDoorEvent firedEvent) {
    verify(stub, times(1)).setEvent(eq(OpenDoorEvent.class.getName()), eq(toBytes(firedEvent)));
  }

  // shared by each nested test class
  // this method is called before each test method
  @BeforeEach
  void setup() {
    contract = new LaunchCodes();
    given(ctx.getStub()).willReturn(stub);
  }

  // Create a nested class for each contract method
  // and add a test method for each test case
  @Nested
  class RegisterStaffCard_Test {
    @Test
    void creates_staff_card_successfully_for_valid_inputs() {
      ///////////////////////////////////////////////
      // Arrange - Set up the proper preconditions //
      ///////////////////////////////////////////////
      String cardID = "staff123"; // non-existing ID
      String cardHolderName = "John Doe"; // arbitrary name
  
      //////////////////////////////////////
      // Act - Execute the contract metod //
      //////////////////////////////////////
      contract.registerStaffCard(ctx, cardID, cardHolderName);
  
      ///////////////////////////////////////////////
      // Assert - Check the resulting side effects //
      ///////////////////////////////////////////////
      Card resultingCard =
          Card.builder()
              .cardID(cardID)
              .cardHolderName(cardHolderName)
              .cardType(CardType.STAFF)
              .build();
  
      createdExactlyOnceInStub(resultingCard);
  
      CloseDoorEvent resultingEvent = CloseDoorEvent.builder().build();
      closeDoorEventFiredOnceInStub(resultingEvent);
  
      verifyNoMoreInteractions(stub);
    }
  }
}

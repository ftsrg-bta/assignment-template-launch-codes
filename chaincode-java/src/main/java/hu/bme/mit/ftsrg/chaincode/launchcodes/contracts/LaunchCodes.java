/* SPDX-License-Identifier: Apache-2.0 */
package hu.bme.mit.ftsrg.chaincode.launchcodes.contracts;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Transaction.TYPE;

@Contract(
    name = "hw-launch-codes",
    info =
        @Info(
            title = "Launch Codes",
            description = "Chaincode for the BTA homework L(a)unchCodes",
            version = "0.1.0",
            license = @License(name = "Apache-2.0"),
            contact =
                @Contact(
                    email = "john.doe@example.com",
                    name = "John Doe",
                    url = "http://example.com")))
@Default
public final class LaunchCodes implements ContractInterface {

  @Transaction(name = "RegisterStaffCard", intent = TYPE.SUBMIT)
  public void registerStaffCard(Context ctx, String cardID, String carHolderName) {}

  @Transaction(name = "RegisterSoldierCard", intent = TYPE.SUBMIT)
  public void registerSoldierCard(Context ctx, String cardID, String carHolderName) {}

  @Transaction(name = "RegisterSecureFacility", intent = TYPE.SUBMIT)
  public void registerSecureFacility(
      Context ctx,
      String facilityID,
      String facilityName,
      String soldierOneID,
      String soldierTwoID) {}

  @Transaction(name = "RequestEntry", intent = TYPE.SUBMIT)
  public void requestEntry(Context ctx, String facilityID, String cardID) {}

  @Transaction(name = "ApproveEntry", intent = TYPE.SUBMIT)
  public void approveEntry(Context ctx, String facilityID, String soldierCardID) {}

  @Transaction(name = "RejectEntry", intent = TYPE.SUBMIT)
  public void rejectEntry(Context ctx, String facilityID, String soldierCardID) {}

  @Transaction(name = "LogEntry", intent = TYPE.SUBMIT)
  public void logEntry(Context ctx, String facilityID, String cardID) {}

  @Transaction(name = "RequestExit", intent = TYPE.SUBMIT)
  public void requestExit(Context ctx, String facilityID, String cardID) {}

  @Transaction(name = "ApproveExit", intent = TYPE.SUBMIT)
  public void approveExit(Context ctx, String facilityID, String soldierCardID) {}

  @Transaction(name = "RejectExit", intent = TYPE.SUBMIT)
  public void rejectExit(Context ctx, String facilityID, String soldierCardID) {}

  @Transaction(name = "LogExit", intent = TYPE.SUBMIT)
  public void logExit(Context ctx, String facilityID, String cardID) {}

  @Transaction(name = "InitiateShiftChange", intent = TYPE.SUBMIT)
  public void initiateShiftChange(
      Context ctx, String facilityID, String newSoldiersID, String oldSoldiersID) {}

  @Transaction(name = "ApproveShiftChange", intent = TYPE.SUBMIT)
  public void approveShiftChange(Context ctx, String facilityID, String oldSoldiersID) {}

  @Transaction(name = "RejectShiftChange", intent = TYPE.SUBMIT)
  public void rejectShiftChange(Context ctx, String facilityID, String oldSoldiersID) {}

  // QUERIES

  @Transaction(name = "GetSecureFacility", intent = TYPE.EVALUATE)
  public String getSecureFacility(Context ctx, String lockID) {
    return null;
  }

  @Transaction(name = "GetAllSecureFacilities", intent = TYPE.EVALUATE)
  public String getAllSecureFacilities(Context ctx) {
    return null;
  }

  @Transaction(name = "GetEntryRequest", intent = TYPE.EVALUATE)
  public String getEntryRequest(Context ctx, String facilityID, String requestTimestampString) {
    return null;
  }

  @Transaction(name = "GetAllEntryRequests", intent = TYPE.EVALUATE)
  public String getAllEntryRequests(Context ctx) {
    return null;
  }

  @Transaction(name = "GetExitRequest", intent = TYPE.EVALUATE)
  public String getExitRequest(Context ctx, String facilityID, String requestTimestampString) {
    return null;
  }

  @Transaction(name = "GetAllExitRequests", intent = TYPE.EVALUATE)
  public String getAllExitRequests(Context ctx) {
    return null;
  }

  @Transaction(name = "GetShiftChangeRequest", intent = TYPE.EVALUATE)
  public String getShiftChangeRequest(
      Context ctx, String facilityID, String requestTimestampString) {
    return null;
  }

  @Transaction(name = "GetAllShiftChangeRequests", intent = TYPE.EVALUATE)
  public String getAllShiftChangeRequests(Context ctx) {
    return null;
  }

  @Transaction(name = "GetCard", intent = TYPE.EVALUATE)
  public String getCard(Context ctx, String cardID) {
    return null;
  }

  @Transaction(name = "GetAllCards", intent = TYPE.EVALUATE)
  public String getAllCards(Context ctx) {
    return null;
  }
}

/* SPDX-License-Identifier: Apache-2.0 */
package hu.bme.mit.ftsrg.bta.launchcodes.assets;

/// This enum represents the possible statuses of a shift change request.
public enum ShiftChangeRequestStatus {
  PENDING, // Request is pending approval
  APPROVED, // Request has been approved by the old soldier
  REJECTED // Request has been rejected
}

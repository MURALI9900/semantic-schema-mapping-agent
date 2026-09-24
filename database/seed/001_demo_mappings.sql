INSERT INTO semantic_schema_mapping
(legacy_code,business_domain,business_term,definition,source_system)
VALUES
('TXN_CD_889','LOAN','Loan Repayment','Represents a loan repayment transaction initiated by an authorized banking officer.','DEMO_CORE_BANKING'),
('LOAN_CLR_221','LOAN','Loan Clearance','Represents a loan clearance or closure workflow after outstanding obligations are settled.','DEMO_CORE_BANKING'),
('REFUND_117','PAYMENTS','Payment Refund','Represents a refund operation against an eligible banking payment.','DEMO_CORE_BANKING'),
('RESCH_305','LOAN','Loan Rescheduling','Represents a loan rescheduling operation subject to configured business rules.','DEMO_CORE_BANKING')
ON CONFLICT (legacy_code) DO NOTHING;

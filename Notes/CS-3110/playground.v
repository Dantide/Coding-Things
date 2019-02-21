Theorem and_to_imp : forall P Q R : Prop,
  (P /\ Q -> R) -> (P -> (Q -> R)).
Proof.
  intros P Q R evPandQimpR evP evQ.
  apply evPandQimpR.
  split.
  all: assumption.
Qed.

Print and_to_imp.

Definition and_to_imp

Theorem and_to_imp2 : forall P Q R : Prop,
  (P /\ Q -> R) -> P -> Q -> R.